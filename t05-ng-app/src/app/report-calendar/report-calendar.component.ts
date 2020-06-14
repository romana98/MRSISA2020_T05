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
  selector: 'app-report-calendar',
  templateUrl: './report-calendar.component.html',
  styleUrls: ['./report-calendar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ReportCalendarComponent implements OnInit {

  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  apps:any[];

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,private router: Router) {}

  async ngOnInit(){

     // @ts-ignore
    this.apps = await this.http.get("https://eclinic05.herokuapp.com/appointment/getReportAppointments").toPromise();

    this.addEvents();
  }

  addEvents()
  {
    this.apps.forEach(app => {
      let a = new Date(app.date);
      let s = new Date(a.setDate(a.getDate()+1));
      let e = new Date(a.getTime() + app.duration*60000);
      let v = new Date(app.date);

      this.events.push(
        {
          start: subDays(s, 1),
          end: subDays(e, 1),
          title: 'Start: ' + v.toISOString().substring(0,10) + " " + v.toISOString().substring(11,16)
            + ', End: ' +  new Date(v.getTime() + app.duration*60000).toISOString().substring(0,10) + " " + new Date(v.getTime() + app.duration*60000).toISOString().substring(11,16)
            + ", Patient: " + app.patient.name + " " + app.patient.surname +
            ", Doctor: " + app.doctor.name + " " + app.doctor.surname + ", Price: " + app.price,
          color: colors.red
        },
      );
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

}
