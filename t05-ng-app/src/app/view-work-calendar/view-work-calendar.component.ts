import {Component, ChangeDetectionStrategy, ViewChild, TemplateRef, OnInit} from '@angular/core';
import {subDays, isSameDay, isSameMonth} from 'date-fns';
import {CalendarEvent, CalendarView} from 'angular-calendar';
import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient} from "@angular/common/http";
import {Subject} from "rxjs";
import {Router} from "@angular/router";

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  }
};

@Component({
  selector: 'app-view-work-calendar',
  templateUrl: './view-work-calendar.component.html',
  styleUrls: ['./view-work-calendar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ViewWorkCalendarComponent implements OnInit {

  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  works:any[];
  apps:any[];

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,private router: Router) {}

  async ngOnInit(){

    // @ts-ignore
    this.works = await this.http.get("https://eclinic05.herokuapp.com/workCalendar/getWorkCalendar").toPromise();
    // @ts-ignore
    this.apps = await this.http.get("https://eclinic05.herokuapp.com/appointment/getDoctorAppointments").toPromise();

    this.addEvents();
  }

  addEvents()
  {
    this.works.forEach(work => {
      if(work.leave == true)
      {
        this.events.push(
          {
            start: new Date(work.date),
            end: new Date(work.date),
            title: 'On Leave',
            color: colors.blue,
            allDay: true
          },
        );
      }

      this.apps.forEach(app => {
        let w = new Date(work.date);
        w.setHours(parseInt(work.startTime.split(":")[0]), parseInt(work.startTime.split(":")[1]));
        let a = new Date(app.date)
        if(w.getTime() === a.getTime())
        {
          console.log(new Date(work.date));
          console.log(new Date(work.date).setHours(parseInt(work.startTime.split(":")[0]), parseInt(work.startTime.split(":")[1])));
          console.log(subDays(new Date(work.date).setHours(parseInt(work.startTime.split(":")[0]), parseInt(work.startTime.split(":")[1])), 1));
          this.events.push(
            {
              start: subDays(new Date(w.setDate(w.getDate()+1)).setHours(parseInt(work.startTime.split(":")[0]), parseInt(work.startTime.split(":")[1])), 1),
              end: subDays(new Date(w).setHours(parseInt(work.endTime.split(":")[0]), parseInt(work.endTime.split(":")[1])), 1),
              title: 'Start:' + work.startTime + ', End: ' + work.endTime + ', AppointmentType: ' + app.appointmentType.name + ", Patient: " + app.patient.name + " " + app.patient.surname,
              color: colors.red,
              meta: {
                id: app.id,
                name: app.patient.name,
                surname: app.patient.surname,
                email: app.patient.email,
                p_id: app.patient.password,
                insurance_number: app.patient.insurance_number
              }
            },
          );
        }
      })
    })
    this.refresh.next();
  }

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];

  activeDayIsOpen: boolean = false;

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }

  handleEvent(action: string, event: CalendarEvent): void {
    let url = "https://eclinic05.herokuapp.com/patients/canStartApp";

    console.log(event)
    this.http.get(url, {params:{p_id: event.meta.p_id, a_id:event.meta.id}}).subscribe(
      res => {
        if(res === 1)
        {
          this.router.navigate(['/staff/currentAppointment'],{queryParams : {'patient': event.meta.name + ' ' + event.meta.name,
              'insurance': event.meta.insurance_number, 'email': event.meta.email, 'appId' : event.meta.id}});

        }
        else
        {
          this._snackBar.open("Appointment not in session!", "Close", {
            duration: 2000,
          });
        }
      }
    )
  }

}

