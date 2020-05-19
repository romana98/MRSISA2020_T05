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
    start_date_str: '',
    end_date_str: ''
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
    let url = "/clinicAdministrator/addClinicAdministrator"
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
  start_date_str: String;
  end_date_str: String;
}
