package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.tim05.dto.AppointmentDTO;
import com.project.tim05.dto.HallDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.Clinic;
import com.project.tim05.model.Doctor;
import com.project.tim05.model.Hall;
import com.project.tim05.model.WorkCalendar;
import com.project.tim05.repository.AppointmentRespository;
import com.project.tim05.repository.HallRepository;
import com.project.tim05.repository.WorkCalendarRespository;

@Transactional(readOnly = false)
@Service
public class HallService {

	@Autowired
	private HallRepository hr;

	@Autowired
	private AppointmentService as;

	@Autowired
	private ClinicService cs;

	@Autowired
	private AppointmentRespository ar;

	@Autowired
	private WorkCalendarRespository wcr;

	@Autowired
	private DoctorService ds;

	@Autowired
	private WorkCalendarService wcs;

	public int addHall(Hall hall) {

		List<Hall> halls1 = hr.getByName(hall.getName());
		List<Hall> halls2 = hr.getByNumber(hall.getNumber());

		for (Hall h : halls1) {
			if (halls2.contains(h)) {
				return 0;
			}
		}
		hr.save(hall);
		return 1;

	}

	public Hall getHallbyId(int id) {
		return hr.findById(id).orElse(null);
	}

	public ArrayList<Hall> getClinicHalls(int id) {
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			st = conn.prepareStatement("SELECT * FROM halls WHERE clinic = ?");

			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			ArrayList<Hall> lh = new ArrayList<Hall>();
			while (rs.next()) {
				Hall new_hall = new Hall();
				new_hall.setId(rs.getInt("hall_id"));
				new_hall.setName(rs.getString("name"));
				new_hall.setNumber(rs.getInt("number"));
				new_hall.setClinic(cs.getClinicbyId(id));
				lh.add(new_hall);
			}
			st.close();
			conn.close();
			rs.close();

			return lh;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int deleteHall(int id) {
		int success = 0;
		try {
			// Connection connection =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"");

			String query = "DELETE FROM halls WHERE hall_id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			success = ps.executeUpdate();

			connection.close();
			ps.close();

		} catch (SQLException e) {

			success = 0;
		}

		return success;
	}

	public int editHall(String name, int number, int id) {
		Hall h = hr.findById(id).orElse(null);
		if (h == null) {
			return 1;
		}
		List<Hall> halls = hr.getByName(name);

		if (!halls.isEmpty()) {
			for (Hall hall : halls) {
				if (hall.getNumber() == number) {
					return 1;
				}
			}
		}

		h.setName(name);
		h.setNumber(number);
		hr.save(h);
		return 0;
	}

	// metoda koja vraca sale pretrazene po parametru sa radnim kalendarom za zadati
	// datum
	public ArrayList<HallDTO> getAvailableHalls(String param, String value, Date date, int clinic_id) {
		ArrayList<HallDTO> dtos = new ArrayList<HallDTO>();
		// uzimanje svih sala za dati parametar
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			ResultSet rs;

			ArrayList<Hall> lh = new ArrayList<Hall>();

			if (param.equalsIgnoreCase("name")) {

				st = conn.prepareStatement("SELECT * FROM halls WHERE name = ? and clinic = ?");
				st.setString(1, value);
				st.setInt(2, clinic_id);

				rs = st.executeQuery();
				while (rs.next()) {
					Hall new_hall = new Hall();
					new_hall.setId(rs.getInt("hall_id"));
					new_hall.setName(rs.getString("name"));
					new_hall.setNumber(rs.getInt("number"));
					lh.add(new_hall);
				}

			} else if (param.equalsIgnoreCase("number")) {

				st = conn.prepareStatement("SELECT * FROM halls WHERE number = ? and clinic = ?");
				st.setInt(1, Integer.parseInt(value));
				st.setInt(2, clinic_id);

				rs = st.executeQuery();
				while (rs.next()) {
					Hall new_hall = new Hall();
					new_hall.setId(rs.getInt("hall_id"));
					new_hall.setName(rs.getString("name"));
					new_hall.setNumber(rs.getInt("number"));
					lh.add(new_hall);
				}

			}

			if (lh.size() == 0) {
				lh = getClinicHalls(clinic_id);
			}
			// uzimanje pregleda koji su zakazani za zadati datum

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = false ORDER BY date_time ASC");
			java.sql.Date sd = new java.sql.Date(date.getTime());
			st.setDate(1, sd);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
			st.setDate(2, sd1);

			st.setInt(3, clinic_id);

			rs = st.executeQuery();
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment ap;
				ap = as.getAppointmentById(rs.getInt("appointment_id"));
				appointments.add(ap);
			}

			st.close();
			conn.close();
			rs.close();

			// ovde su inicijalizovani svi podaci i moze se krenuti sa algoritmom
			HashMap<Integer, ArrayList<String>> map_of_times = getAvailableTimes(lh, appointments);

			// treba jos napraviti dto

			for (Hall h : lh) {
				HallDTO hdto = new HallDTO();
				hdto.setId(h.getId());
				hdto.setName(h.getName());
				hdto.setNumber(h.getNumber());
				hdto.setTimes(map_of_times.get(h.getId()));
				hdto.setTime(hdto.getTimes().get(0));
				dtos.add(hdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dtos;
	}

	public String getFirstTime(Appointment ap, Hall h, int clinic_id) {
		String first_time = "";
		boolean flag = true;
		Date date = ap.getDateTime();
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		while (first_time.equalsIgnoreCase("")) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
						"");
				PreparedStatement st;
				st = conn.prepareStatement(
						"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = false ORDER BY date_time ASC");
				java.sql.Date sd = new java.sql.Date(date.getTime());
				st.setDate(1, sd);

				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.DATE, 1);
				date = c.getTime();
				java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
				st.setDate(2, sd1);

				st.setInt(3, clinic_id);
				ResultSet rs;

				rs = st.executeQuery();
				while (rs.next()) {
					Appointment new_ap;
					new_ap = as.getAppointmentById(rs.getInt("appointment_id"));
					appointments.add(new_ap);
				}
				conn.close();

			} catch (Exception e) {

			}
			if (getFreeTimes(h, appointments).size() == 0) {
				flag = false;
				continue;
			} else {
				if (flag) {
					// logika da je isti dan u pitanju
					int smallest_distance = 2000;
					String smallest_time = "";
					int aim_start = getTimeMinutes(ap.getDateTime());
					int length = ap.getDuration();
					for (String time : getFreeTimes(h, appointments)) {
						int start_time = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
								+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
						int end_time = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
								+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
						if (end_time - start_time > length) {
							if (aim_start >= end_time) {
								int current_dis = aim_start - end_time + length;
								smallest_time = (smallest_distance > current_dis)
										? (ap.getDateTime().toString().split(" ")[0] + " "
												+ getMinutesToTime(end_time - length))
										: smallest_time;
								smallest_distance = (smallest_distance > current_dis) ? current_dis : smallest_distance;
							} else {
								int current_dis = start_time - aim_start;
								smallest_time = (smallest_distance > current_dis)
										? (ap.getDateTime().toString().split(" ")[0] + " "
												+ getMinutesToTime(start_time))
										: smallest_time;
								smallest_distance = (smallest_distance > current_dis) ? current_dis : smallest_distance;

							}
						}

					}

					first_time = smallest_time;

				} else {
					first_time = ap.getDateTime().toString().split(" ")[0] + " "
							+ getFreeTimes(h, appointments).get(0).split(" -> ")[0];
				}
			}

		}

		return first_time;
	}

	// metoda prima listu sala za kliniku i listu pregleda
	// vraca mapu u kojoj je kljuc id sale a vrednost lista vremena u obliku
	// "pocetak kraj" slobodnog
	// npr {1:{"00:00 -> 09:00","11:00 -> 13:00"}}
	private HashMap<Integer, ArrayList<String>> getAvailableTimes(ArrayList<Hall> halls,
			ArrayList<Appointment> appointments) {
		HashMap<Integer, ArrayList<String>> map_of_times = new HashMap<Integer, ArrayList<String>>();
		for (Hall h : halls) {
			ArrayList<String> free_times = getFreeTimes(h, appointments);
			map_of_times.put(h.getId(), free_times);
		}
		return map_of_times;
	}

	private ArrayList<String> getFreeTimes(Hall h, ArrayList<Appointment> appointments) {
		ArrayList<String> free_times = new ArrayList<String>();
		int pocetak = 0;
		for (Appointment app : appointments) {
			if (app.getHall().getId() == h.getId()) {
				// vreme pocetka pregleda
				int startTime = getTimeMinutes(app.getDateTime());
				// naleteo sam na pregled u toj sali znaci slobodno vreme je od pocetka do tog
				// trenutka
				if (pocetak < startTime) {
					String free_time = getMinutesToTime(pocetak) + " -> " + getMinutesToTime(startTime);
					free_times.add(free_time);
				}
				// a zatim postavljam za kraj tog pregleda nov pocetak za sledecu iteraciju
				// petlje
				pocetak = startTime + app.getDuration();
			}

		}
		if (pocetak < 60 * 24) {
			free_times.add(getMinutesToTime(pocetak) + " -> " + "24:00");
		}
		return free_times;
	}
	
	public int tryReserve(Hall h, Appointment a, Date date, int clinic_id) {
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = false ORDER BY date_time ASC");
			java.sql.Date sd = new java.sql.Date(date.getTime());
			st.setDate(1, sd);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
			st.setDate(2, sd1);

			st.setInt(3, clinic_id);

			ResultSet rs = st.executeQuery();
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment ap;
				ap = as.getAppointmentById(rs.getInt("appointment_id"));
				appointments.add(ap);
			}

			ArrayList<String> free_times = getFreeTimes(h, appointments);

			int pocetak = getTimeMinutes(a.getDateTime());
			int kraj = pocetak + a.getDuration();
			
		

			for (String time : free_times) {
				int from = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
						+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
				int to = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
						+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
				if (pocetak >= from && kraj <= to) {
					return 0;

				}
			}

			st = conn.prepareStatement("SELECT * FROM public.halls where clinic = ?");

			st.setInt(1, clinic_id);

			rs = st.executeQuery();
			while (rs.next()) {
				Hall hall;
				hall = hr.findById(rs.getInt("hall_id")).orElse(null);
				ArrayList<String> all_times = getFreeTimes(hall, appointments);
				for (String time : all_times) {
					int from = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
							+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
					int to = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
							+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
					if (pocetak >= from && kraj <= to) {
						return 2;

					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 1;
	}
	@Transactional(readOnly = false)
	//DATE SLUZI SAMO DA BI UZEO WORK CALENDARE ZA DATUM ZAKAZIVANJA
	public int reserveHall(Hall h, Appointment a, Date date, int clinic_id) {
		/*EntityManagerFactory factory = 
				  Persistence.createEntityManagerFactory("factory");
		EntityManager em = factory.createEntityManager();
		em.find(Appointment.class, a.getId(),LockMode.OPTIMISTIC_FORCE_INCREMENT);
		em.lock(per_a, LockMode.OPTIMISTIC_FORCE_INCREMENT);*/
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = false ORDER BY date_time ASC");
			java.sql.Date sd = new java.sql.Date(date.getTime());
			st.setDate(1, sd);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
			st.setDate(2, sd1);

			st.setInt(3, clinic_id);

			ResultSet rs = st.executeQuery();
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment ap;
				ap = as.getAppointmentById(rs.getInt("appointment_id"));
				appointments.add(ap);
			}

			ArrayList<String> free_times = getFreeTimes(h, appointments);

			int pocetak = getTimeMinutes(a.getDateTime());
			int kraj = pocetak + a.getDuration();
			
		

			for (String time : free_times) {
				int from = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
						+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
				int to = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
						+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
				if (pocetak >= from && kraj <= to) {
					a.setHall(h);
					a.setRequest(false);
					h.getAppointments().add(a);
					Doctor d = initializeAndUnproxy.initAndUnproxy(a.getDoctor());
					Set<WorkCalendar> wc = initializeAndUnproxy.initAndUnproxy(d.getWorkCalendar());
					hr.save(h);
					ar.save(a);
					ar.flush();
					for (WorkCalendar wece : wc) {
						if (wece.getDate().getDay() == a.getDateTime().getDay()) {
							System.out.println(a.getDateTime().toString());
							if (wece.getStart_time()
									.equalsIgnoreCase(a.getDateTime().toString().split(" ")[1].substring(0, 5))) {
								wece.setRequest(false);
								wece.setStart_time(getMinutesToTime(pocetak));
								wcr.save(wece);
								return 0;
							}
						} 
					}

				}
			}

			st = conn.prepareStatement("SELECT * FROM public.halls where clinic = ?");

			st.setInt(1, clinic_id);
  
			rs = st.executeQuery();
			while (rs.next()) {
				Hall hall;
				hall = hr.findById(rs.getInt("hall_id")).orElse(null);
				ArrayList<String> all_times = getFreeTimes(hall, appointments);
				for (String time : all_times) {
					int from = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
							+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
					int to = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
							+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
					if (pocetak >= from && kraj <= to) {
						a.setHall(h);
						a.setRequest(false);
						h.getAppointments().add(a);
						Doctor d = initializeAndUnproxy.initAndUnproxy(a.getDoctor());
						Set<WorkCalendar> wc = initializeAndUnproxy.initAndUnproxy(d.getWorkCalendar());
						hr.save(h);
						ar.save(a);
						for (WorkCalendar wece : wc) {
							if (wece.getDate().toString().split(" ")[0]
									.equalsIgnoreCase(a.getDateTime().toString().split(" ")[0])) {
								if (wece.getStart_time()
										.equalsIgnoreCase(a.getDateTime().toString().split(" ")[1].substring(0, 5))) {
									wece.setRequest(false);
									wcr.save(wece);
									return 2;
								}
							}
						}

					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public int reserveOperation(Hall h, Appointment a, Date date, int clinic_id) {
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = false ORDER BY date_time ASC");
			java.sql.Date sd = new java.sql.Date(date.getTime());
			st.setDate(1, sd);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
			st.setDate(2, sd1);

			st.setInt(3, clinic_id);

			ResultSet rs = st.executeQuery();
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment ap;
				ap = as.getAppointmentById(rs.getInt("appointment_id"));
				appointments.add(ap);
			}

			ArrayList<String> free_times = getFreeTimes(h, appointments);

			int pocetak = getTimeMinutes(date);
			int kraj = pocetak + a.getDuration();
			
		

			for (String time : free_times) {
				int from = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
						+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
				int to = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
						+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
				if (pocetak >= from && kraj <= to) {
					a.setHall(h);
					a.setRequest(false);
					h.getAppointments().add(a);
					Doctor d = initializeAndUnproxy.initAndUnproxy(a.getDoctor());
					Set<WorkCalendar> wc = initializeAndUnproxy.initAndUnproxy(d.getWorkCalendar());
					hr.save(h);
					ar.save(a);
					for (WorkCalendar wece : wc) {
						if (wece.getDate().getDay() == a.getDateTime().getDay()) {
							System.out.println(a.getDateTime().toString());
							if (wece.getStart_time()
									.equalsIgnoreCase(a.getDateTime().toString().split(" ")[1].substring(0, 5))) {
								wece.setRequest(false);
								wece.setStart_time(getMinutesToTime(pocetak));
								wece.setEnd_time(getMinutesToTime(kraj));
								wece.getDate().setDate(date.getDate());
								a.setDateTime(date);
								wcr.save(wece);
								return 0;
							}
						} 
					}

				}
			}
		}
		catch(Exception e) {
			
		}
		return 1;
	}

	@Scheduled(cron = "0 0 0 * * ?")
	// @Scheduled(cron = "0 * * ? * *")
	public void dailyReservation() {
		final Calendar cal = Calendar.getInstance();
		System.out.println("IM DOING SCHEDULED TASK -----");

		// cal.add(Calendar.DATE, -1);
		cal.add(Calendar.DATE, 1);
		Date date = cal.getTime();
		System.out.println("IM DOING SCHEDULED TASK -----");
		List<Clinic> clinics = cs.getClinics();
		for (Clinic c : clinics) {
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			for (AppointmentDTO a : as.getAppointmentRequests(date, c.getId())) {
				appointments.add(as.getAppointmentById(a.getId()));

			}
			ArrayList<Hall> halls = getClinicHalls(c.getId());
			for (Appointment a : appointments) {
				for (Hall h : halls) {
					int reservation_success = reserveHall(h, a, date, c.getId());

					if (reservation_success == 0) {
						System.out.println("Reserved");
						break;
					} else if (reservation_success == 1) {
						String time = getFirstTime(a, h, c.getId());
						int app_start = Integer.parseInt(time.substring(11, 16).split(":")[0]) * 60
								+ Integer.parseInt(time.substring(11, 16).split(":")[1]);
						int app_end = app_start + a.getDuration();
						int dr_start = Integer.parseInt(a.getDoctor().getWorkStart().split(":")[0]) * 60
								+ Integer.parseInt(a.getDoctor().getWorkStart().split(":")[1]);
						int dr_end = Integer.parseInt(a.getDoctor().getWorkEnd().split(":")[0]) * 60
								+ Integer.parseInt(a.getDoctor().getWorkEnd().split(":")[1]);

						Set<WorkCalendar> wcs1 = initializeAndUnproxy.initAndUnproxy(a.getDoctor().getWorkCalendar());

						boolean busy = false;

						if ((app_start < dr_start || app_start > dr_end) || (app_end < dr_start || app_end > dr_end)) {
							busy = true;
						}

						for (WorkCalendar wc : wcs1) {
							if (time.substring(0, 10).equalsIgnoreCase(wc.getDate().toString().substring(0, 10))) {
								int wc_start = Integer.parseInt(wc.getStart_time().split(":")[0]) * 60
										+ Integer.parseInt(wc.getStart_time().split(":")[1]);
								int wc_end = Integer.parseInt(wc.getEnd_time().split(":")[0]) * 60
										+ Integer.parseInt(wc.getEnd_time().split(":")[1]);
								if ((app_start > wc_start && app_start < wc_end)
										|| (app_end > wc_start && app_end < wc_end)
										|| (app_start == wc_start && app_end == wc_end)) {
									busy = true;
								}
							}
						}
						Doctor new_d = a.getDoctor();
						Doctor doc2 = null;
						if (busy) {
							ArrayList<Doctor> doctors = ds
									.getClinicDoctorsbyAppointmentType(a.getAppointmentType().getId(), c.getId());
							for (Doctor d : doctors) {
								boolean available = true;
								int dr1_start = Integer.parseInt(d.getWorkStart().split(":")[0]) * 60
										+ Integer.parseInt(d.getWorkStart().split(":")[1]);
								int dr1_end = Integer.parseInt(d.getWorkEnd().split(":")[0]) * 60
										+ Integer.parseInt(d.getWorkEnd().split(":")[1]);
								if ((app_start < dr1_start || app_start > dr1_end)
										|| (app_end < dr1_start || app_end > dr1_end)) {
									available = false;
								}
								Set<WorkCalendar> work_times = initializeAndUnproxy.initAndUnproxy(d.getWorkCalendar());
								for (WorkCalendar wc : work_times) {
									if (time.substring(0, 10)
											.equalsIgnoreCase(wc.getDate().toString().substring(0, 10))) {
										int wc_start = Integer.parseInt(wc.getStart_time().split(":")[0]) * 60
												+ Integer.parseInt(wc.getStart_time().split(":")[1]);
										int wc_end = Integer.parseInt(wc.getEnd_time().split(":")[0]) * 60
												+ Integer.parseInt(wc.getEnd_time().split(":")[1]);
										if ((app_start > wc_start && app_start < wc_end)
												|| (app_end > wc_start && app_end < wc_end)
												|| (app_start == wc_start && app_end == wc_end)) {
											available = false;
										}
									}
								}

								if (available) {
									doc2 = d;
									break;
								}
							}
						}
						app_start = getTimeMinutes(a.getDateTime());
						app_end = app_start + 30;
						WorkCalendar target_wc = null;
						Set<WorkCalendar> times = new_d.getWorkCalendar();
						for (WorkCalendar wc1 : times) {
							int wc_start = Integer.parseInt(wc1.getStart_time().split(":")[0]) * 60
									+ Integer.parseInt(wc1.getStart_time().split(":")[1]);
							int wc_end = Integer.parseInt(wc1.getEnd_time().split(":")[0]) * 60
									+ Integer.parseInt(wc1.getEnd_time().split(":")[1]);
							if (wc_start == app_start && wc_end == app_end) {
								target_wc = wc1;
								break;
							}
						}
						if (doc2 != null) {
							new_d = doc2;
						} else {
							if (busy) {
								break;
							}
						}

						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						java.util.Date new_date = null;
						java.util.Date new_date_full = null;

						try {
							new_date = formatter.parse(time);
							new_date_full = formatter2.parse(time);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println("here goes the shit");

						target_wc.setDate(new_date);
						target_wc.setDoctor(new_d);
						target_wc.setRequest(false);
						app_start = Integer.parseInt(time.substring(11, 16).split(":")[0]) * 60
								+ Integer.parseInt(time.substring(11, 16).split(":")[1]);
						app_end = app_start + a.getDuration();
						target_wc.setStart_time(getMinutesToTime(app_start));
						target_wc.setEnd_time(getMinutesToTime(app_end));
						a.setDoctor(new_d);

						a.setHall(h);
						a.setRequest(false);
						a.setDateTime(new_date_full);
						Set<Appointment> apps = h.getAppointments();
						apps.add(a);
						h.setAppointments(apps);

						as.updateAppointment(a);
						updateHall(h);
						wcs.updateCalendar(target_wc);

						System.out.println("Reserved 1");

						break;
					} else {
						continue;
					}
				}
			}
		}

	}

	// metoda prima datum i vraca broj minuta od pocetka dana
	@SuppressWarnings("deprecation")
	public int getTimeMinutes(Date d) {
		return d.getHours() * 60 + d.getMinutes();

	}

	public String getMinutesToTime(int minutes) {
		int minute = minutes % 60;
		int hour = minutes / 60;
		String res = hour + ":" + minute;
		if (minute < 10) {
			res = hour + ":" + minute + "0";
		}
		if (hour < 10) {
			res = "0" + res;
		}

		return res;
	}

	public void updateHall(Hall h) {
		hr.save(h);
	}

	public boolean predefinedHallAvailable(Hall h, Appointment a, Date date, int clinic_id) {
		Connection conn;
		try {
			// conn =
			// DriverManager.getConnection("jdbc:postgresql://ec2-54-247-89-181.eu-west-1.compute.amazonaws.com:5432/d1d2a9u0egu6ja",
			// "xslquaksjvvetl",
			// "791a6dd69c36471adccf1118066dae6841cf2b7145d82831471fdd6640e5d99a");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "");

			PreparedStatement st;

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? and request = false ORDER BY date_time ASC");
			java.sql.Date sd = new java.sql.Date(date.getTime());
			st.setDate(1, sd);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, 1);
			java.sql.Date sd1 = new java.sql.Date(c.getTimeInMillis());
			st.setDate(2, sd1);

			st.setInt(3, clinic_id);

			ResultSet rs = st.executeQuery();
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			while (rs.next()) {
				Appointment ap;
				ap = as.getAppointmentById(rs.getInt("appointment_id"));
				appointments.add(ap);
			}
			
			int pocetak = getTimeMinutes(a.getDateTime());
			int kraj = pocetak + a.getDuration();
			
			st = conn.prepareStatement("SELECT * FROM public.halls where clinic = ?");

			st.setInt(1, clinic_id);
			
			rs = st.executeQuery();
			while (rs.next()) {
				Hall hall;
				hall = hr.findById(rs.getInt("hall_id")).orElse(null);
				ArrayList<String> all_times = getFreeTimes(hall, appointments);
				for (String time : all_times) {
					int from = Integer.parseInt(time.split(" -> ")[0].split(":")[0]) * 60
							+ Integer.parseInt(time.split(" -> ")[0].split(":")[1]);
					int to = Integer.parseInt(time.split(" -> ")[1].split(":")[0]) * 60
							+ Integer.parseInt(time.split(" -> ")[1].split(":")[1]);
					if (pocetak >= from && kraj <= to) {
						return true;

					}
				}
			}
			
		} catch (Exception e) {

		}
		return false;
	}

}
