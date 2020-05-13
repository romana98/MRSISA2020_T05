import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'initial-change-password',
  templateUrl: './initial-change-password.component.html',
  styleUrls: ['./initial-change-password.component.css']
})
export class InitialChangePasswordComponent implements OnInit {

  model: passwordModel = {
      password: '',
      password2: ''
  }

  hide : boolean = true;
  hide1: boolean = true;

  constructor(private _snackBar: MatSnackBar, private http : HttpClient, private authservice : AuthenticationService) {

  }

  ngOnInit(): void {

  }

  changePassword(): void{
    let url = "http://localhost:8081/auth/changePassword"
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Password successfully changed!", "Close", {
          duration: 2000,
        });

      },
      err => {
         this._snackBar.open("Error has occurred while changing your password!", "Close", {
            duration: 2000,
          });
      }
    );
  }

  checkPassword():boolean
  {
    if(this.model.password === this.model.password2)
    {
      return true;
    }
    else
    {
      this._snackBar.open("Passwords do not match!", "Close", {
        duration: 2000,
      });
      return false;
    }

  }

}

export interface passwordModel{
 password: String;
 password2: String;
}
