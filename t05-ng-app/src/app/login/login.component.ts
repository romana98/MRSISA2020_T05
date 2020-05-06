import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  
  username : String = '';
  password : String = '';
  password2 :String = '';

  myModel : model = {
    email : "",
    password : ""
  }

  status : boolean = false;

  model: patientModel = {
    email : '',
    password : '',
    name : '',
    surname : '',
    address : '',
    city : '',
    country : '',
    phone_number : '',
    insurance_number : ''
  }

  hide : boolean = true;
  hide1: boolean = true;

  response : any;

  constructor(private _snackBar: MatSnackBar, private http : HttpClient, private authservice : AuthenticationService) { }

  ngOnInit(): void {
    this.status = false;
  }

  loginAuth(){
    console.log(this.status);
    this.authservice.authenticate(this.myModel).then( num => {
      this.status = (num === 401);
      document.getElementById('validation').style.display = 'block'}
    );
    /*console.log(newStatus);
    this.status = newStatus;*/
      
  }

  registerPatient(): void{
    let url = "http://localhost:8081/registrationRequests/register"
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Your registration request has been sent! Your activation link will be sent to you soon.", "Close", {
          duration: 2000,
        });

      },
      err => {
        if(err.status == 409)
        {
          console.log(err.error)
          if(err.error === 'email'){
            this._snackBar.open("Email already taken!", "Close", {
              duration: 2000,
            });
          }else{
            this._snackBar.open("Insurance number already taken!", "Close", {
              duration: 2000,
            });
          }


        }
        else
        {
          this._snackBar.open("Error has occurred while registering your profile!", "Close", {
            duration: 2000,
          });

        }
      }
    );
  }

  checkPassword(): boolean{
    var pass1 = this.model.password;
    var pass2 = this.password2;

    return pass1 === pass2;
  }

}

export interface model{
  email: String;
  password : String;
}

export interface patientModel{
  email : string |RegExp;
  password : string;
  name : string |RegExp;
  surname : string |RegExp;
  address : string |RegExp;
  city : string |RegExp;
  country : string |RegExp;
  phone_number : string |RegExp;
  insurance_number : string |RegExp;
}
