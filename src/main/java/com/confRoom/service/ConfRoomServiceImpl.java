package com.confRoom.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.confRoom.exception.MissingEntityException;
import com.confRoom.model.Booking;
import com.confRoom.model.Building;
import com.confRoom.model.ConfRoom;
import com.confRoom.model.Floor;

import com.confRoom.repository.BookingRepository;
import com.confRoom.repository.BuildingRepository;

import com.confRoom.repository.ConfRoomRepository;
import com.confRoom.repository.FloorRepository;

@Service
public class ConfRoomServiceImpl implements ConfRoomService {

	private static final int MAX_DAYS=10;
	@Autowired
	private BuildingRepository buildingRepo;
	@Autowired
	private ConfRoomRepository confRoomRepo;
	@Autowired
	private FloorRepository floorRepo;
	@Autowired
	private FloorService floorService;
	@Autowired
	private BookingRepository bookingRepo;


	

	private Boolean isNonOverlapTomorrowYesterday(int buildingId,int floorId,int confRoomId, String date,Date currStartTime,Date currEndTime) throws ParseException {

		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");

		LocalDate todayDate = LocalDate.parse(date); // SPLIT METHOD

		if (currEndTime.before(currStartTime)) { // SPECIAL CASE (NEW TILL MIDNIGHT BOOKING): check next day

			LocalDate tomorrowDate = todayDate.plusDays(1);

			List<Booking> tomorrowBookings = bookingRepo.findByBuildingIdAndFloorIdAndConfRoomIdAndDateOrderByStartTimeAsc(buildingId,floorId,confRoomId,tomorrowDate.toString());
				
			if (!tomorrowBookings.isEmpty()) {
				Booking tomorrowBooking = tomorrowBookings.getFirst();
				Date tomorrowStartTime = parser.parse(tomorrowBooking.getStartTime());

				if (tomorrowStartTime.before(currEndTime))
					return false;
			}
		}

		LocalDate yesterdayDate = todayDate.minusDays(1); // SPECIAL CASE (NEW FROM MIDNIGHT BOOKING): check previous
		// day

		List<Booking> yesterdayBookings = bookingRepo.findByBuildingIdAndFloorIdAndConfRoomIdAndDateOrderByStartTimeAsc(buildingId,floorId,confRoomId,yesterdayDate.toString());
		if (!yesterdayBookings.isEmpty()) {
			Booking yesterdayBooking = yesterdayBookings.getLast();
			Date yesterdayEndTime = parser.parse(yesterdayBooking.getStartTime());
			Date yesterdayStartTime = parser.parse(yesterdayBooking.getEndTime());
			if (yesterdayEndTime.before(yesterdayStartTime) && yesterdayEndTime.after(currStartTime))
				return false;
		}
		return true;

	}

	public Boolean isRoomAvailable(int buildingId,int floorId,int confRoomId, String date, String startTimeInput, String endTimeInput) throws ParseException {

		// call 3 times instead of getting entire object

		List<Booking> bookingsOfDay = bookingRepo.findByBuildingIdAndFloorIdAndConfRoomIdAndDateOrderByStartTimeAsc(buildingId,floorId,confRoomId,date); // get booking by date
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		Date currStartTime = parser.parse(startTimeInput);
		Date currEndTime = parser.parse(endTimeInput);

		if(!isNonOverlapTomorrowYesterday(buildingId,floorId,confRoomId,date,currStartTime,currEndTime))
			return false;


		if (bookingsOfDay == null)
			return true;

		for (Booking bookingEntry : bookingsOfDay) {

			Date startTime = parser.parse(bookingEntry.getStartTime());
			Date endTime = parser.parse(bookingEntry.getEndTime());

			// MIDNIGHT BOOKING CHECKS

			if (endTime.before(startTime) && (currStartTime.after(startTime) || currStartTime.equals(startTime)
					|| currEndTime.after(startTime)))
				return false; // MIDNIGHT BOOKING ALREADY PRESENT

			if (currEndTime.before(currStartTime) && (currStartTime.before(startTime) || currStartTime.equals(startTime)
					|| currStartTime.before(endTime)))
				return false; // NEW MIDNIGHT BOOKING

			if (endTime.before(startTime) && currEndTime.before(currStartTime))
				return false; // MULTIPLE MIDNIGHT BOOKING

			/*
			 * if(startTime.after(currEndTime)) //OPTIMIZATION break; // AVOIDED BECAUSE OF
			 * MIDNIGHT CASES
			 */

			// NORMAL NON OVERLAP
			if ((currEndTime.before(startTime)) || (currStartTime.after(endTime)))
				continue;

			else if ((currEndTime.equals(startTime)) || (currStartTime.equals(endTime)))
				continue;

			else
				return false;

		}

		return true;

	}
	public  Boolean isCapacitySufficient(ConfRoom confRoom, int capacity) {

		if (confRoom.getMaxCapacity() < capacity) {
			return false;
		}
		return true;
	}


	public void isConfRoomPresent(int buildingId,int floorId, int confRoomId) {

		floorService.isFloorPresent(buildingId,floorId);

		if(!confRoomRepo.existsById(confRoomId))
		{
			throw new MissingEntityException("Exception occured: The requested room is not present");
		}
		return;
	}



	public ConfRoom addConfRoom(ConfRoom confRoom,int buildingId,int floorId) {


		Optional<Floor> floor= floorRepo.findById(floorId);

		if(floor==null) return null;
		floor.get().setConfRoom(confRoom);
		
		
		return confRoomRepo.save(confRoom);	
	}


	public List<ConfRoom> getRoomsByRequirements(int buildingId, int floorId, String date, String startTime, String endTime, int capacity) throws ParseException {

		floorService.isFloorPresent(buildingId, floorId);

		Optional<Floor> floor = floorRepo.findById(floorId); 
		if (floor == null)
			return null;

		List<ConfRoom> confRooms = floor.get().getConfRooms();

		List<ConfRoom> confRoomsResult = new ArrayList<ConfRoom>();

		for (ConfRoom confRoom : confRooms) {

			

			if (isRoomAvailable(buildingId,floorId,confRoom.getConfRoomId(), date, startTime, endTime) && isCapacitySufficient(confRoom, capacity)) {


				confRoomsResult.add(confRoom);

			}
		}

		return confRoomsResult;
	}

	public List<ArrayList<ConfRoom> > getSuggestedRooms(int buildingId, int floorId, String date, String startTime, String endTime, int capacity, int days)
			throws ParseException {

		floorService.isFloorPresent(buildingId, floorId);


		if (days > MAX_DAYS) {
			System.out.println("We cannot provide data for more than 10 days");
			return null;
		}
		List<ArrayList<ConfRoom>> suggestedRooms = new ArrayList<ArrayList<ConfRoom> >();


		ArrayList<ConfRoom> confRooms = new ArrayList<ConfRoom>();


		LocalDate currDate = LocalDate.parse(date);

		for (int i = 0; i < days; i++) {

			String nextDateString = currDate.toString();
			currDate = currDate.plusDays(1);
			confRooms= (ArrayList<ConfRoom>) getRoomsByRequirements(buildingId, floorId, nextDateString,startTime,endTime, capacity); 

			suggestedRooms.add(confRooms);
		}
		return suggestedRooms;
	}

	/*
	 * public String getAddress(ConfRoom confRoom) { Building building =
	 * buildingRepo.getBuildingById(confRoom.getBuildingId()); return
	 * "Address:    Building Name = " + building.getBuildingName() +
	 * ", Floor Name = " + building.getFloor(confRoom.getFloorId()).getFloorName() +
	 * ", Conference Room Name = " + confRoom.getConfRoomName(); }
	 */

}
