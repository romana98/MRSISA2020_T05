import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import { Session } from 'protractor';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username : String = '';
  password : String = '';

  myModel : model = {
    email : "admin@example.com",
    password : "123"
  }

  response : any;

  constructor(private http : HttpClient, private authservice : AuthenticationService) { }

  ngOnInit(): void {
  }

  loginAuth(){
      this.authservice.authenticate(this.myModel);
      console.log(sessionStorage.getItem('role'));
  }

}

export interface model{
  email: String;
  password : String;
}
