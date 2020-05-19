import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-doctor-form',
  templateUrl: './add-nurse-form.component.html',
  styleUrls: ['./add-nurse-form.component.css']
})
export class AddNurseFormComponent implements OnInit {

  model: nurseModel = {
    name : '',
    surname : '',
    email : '',
    password : '',
    workEnd : '',
    workStart : '',
  }

  hide: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) {

  }

  ngOnInit(): void {

  }

  addNurse(): void{
    let url =  "http://localhost:8081/nurse/addNurse"
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Nurse added successfully", "Close", {
          duration: 2000,
        });

      },
      err => {
        if(err.status == 409){
          this._snackBar.open("Nurse with that email already exists!", "Close", {
            duration: 2000,
          });
        }else{
          this._snackBar.open("Error has occurred while adding nurse", "Close", {
            duration: 2000,
          });
        }
      }
    );

  }
}

export interface nurseModel
{
  name: string;
  surname: string;
  email : string;
  password : string;
  workStart : string;
  workEnd : string;

}
