import {Component, ChangeDetectionStrategy, ViewChild, TemplateRef, OnInit} from '@angular/core';
import {startOfDay, subDays, addDays, endOfMonth, isSameDay, isSameMonth, addHours} from 'date-fns';
import {CalendarEvent, CalendarView} from 'angular-calendar';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
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

  themecolor: any = '#0a5ab3'


  constructor() {}

  ngOnInit(){

//Uzmes sve app i work za id ulogovanog doktora
// iteriras po danima pa po pregledima i dodajes ih u calendar event
    /*
    this.events.push(
      {
        start: subDays(startOfDay(new Date()), 1),
        end: addDays(new Date(), 1),
        title: 'A 3 day event',
        color: colors.red
      },
    );

    */

  }



  events: any = [
    {
      start: new Date(),
      end: new Date(),
      title: 'title event 1',
      color: colors.red,
    },
    {
      start: new Date(),
      end: new Date(),
      title: 'title event 2',
      color: colors.yellow,
    }
  ]

  activeDayIsOpen: boolean = true;

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
export interface workModel
{
  name: string;
  address: string | RegExp;
  description: string | RegExp;
  api: object;
}
