import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-leave-request-form',
  templateUrl: './add-leave-request.component.html',
  styleUrls: ['./add-leave-request.component.css']
})
export class AddLeaveRequestComponent implements OnInit{

  model: leaveRequest = {
    start_date: new Date(),
    end_date: null,
    startDate: '',
    endDate: '',
    email: '',
    name: '',
    surname: ''
  }

  today:Date;
  minEnd: Date;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient){

  }


  ngOnInit(): void{
    this.today = new Date();
    this.minEnd = new Date();
  }

  MakeRequest(): void{
    let url = "http://localhost:8081/leaveRequest/makeLeaveRequest"
    this.model.startDate = this.model.start_date.getDate() + "/" + (this.model.start_date.getMonth()+1) + "/" + this.model.start_date.getFullYear();
    this.model.endDate = this.model.end_date.getDate() + "/" + (this.model.end_date.getMonth()+1) + "/" + this.model.end_date.getFullYear();
    console.log(this.model.startDate)
    console.log(this.model.endDate)
    this.http.post(url, this.model).subscribe(
      res => {
        this._snackBar.open("Request made successfully!", "Close", {
          duration: 2000,
        });

      },
      err => {
        if(err.status == 409)
        {
          this._snackBar.open("You have scheduled appointments/operation in selected period! Request can't be made!", "Close", {
            duration: 2000,
          });

        }
        else
        {
          this._snackBar.open("Error has occurred while making request!", "Close", {
            duration: 2000,
          });

          console.log(err);
        }
      }
    );

  }
}

export interface leaveRequest
{
  start_date: Date;
  end_date: Date;
  startDate: String;
  endDate: String;
  email: String;
  name: String;
  surname: String;
}
