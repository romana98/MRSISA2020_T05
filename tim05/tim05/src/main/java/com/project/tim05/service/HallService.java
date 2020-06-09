package com.project.tim05.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.tim05.dto.HallDTO;
import com.project.tim05.model.Appointment;
import com.project.tim05.model.Hall;
import com.project.tim05.repository.HallRepository;

@Service
public class HallService {

	@Autowired
	private HallRepository hr;

	@Autowired
	private AppointmentService as;

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

			if (param.equalsIgnoreCase("name")) {

				st = conn.prepareStatement("SELECT * FROM halls WHERE name = ? and clinic = ?");
				st.setString(1, value);
				st.setInt(2, clinic_id);
			} else {

				st = conn.prepareStatement("SELECT * FROM halls WHERE number = ? and clinic = ?");
				st.setInt(1, Integer.parseInt(value));
				st.setInt(2, clinic_id);

			}

			ResultSet rs = st.executeQuery();
			ArrayList<Hall> lh = new ArrayList<Hall>();
			while (rs.next()) {
				Hall new_hall = new Hall();
				new_hall.setId(rs.getInt("hall_id"));
				new_hall.setName(rs.getString("name"));
				new_hall.setNumber(rs.getInt("number"));
				lh.add(new_hall);
			}

			if (lh.size() == 0) {
				lh = getClinicHalls(clinic_id);
			}
			// uzimanje pregleda koji su zakazani za zadati datum

			st = conn.prepareStatement(
					"SELECT * FROM public.appointments where date_time between ? and ? and clinic = ? ORDER BY date_time ASC");
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
				dtos.add(hdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dtos;
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
				String free_time = getMinutesToTime(pocetak) + " -> " + getMinutesToTime(startTime);
				free_times.add(free_time);
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

	// metoda prima datum i vraca broj minuta od pocetka dana
	@SuppressWarnings("deprecation")
	private int getTimeMinutes(Date d) {
		return d.getHours() * 60 + d.getMinutes();

	}

	private String getMinutesToTime(int minutes) {
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

}
