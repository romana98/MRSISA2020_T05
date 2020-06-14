import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Params, Router} from "@angular/router";
import * as jwt_decode from "jwt-decode";

@Component({
  selector: 'activation-link',
  templateUrl: './activation-link.component.html',
  styleUrls: ['./activation-link.component.css']
})
export class ActivationLinkComponent implements OnInit {

  model: activateModel = {
    id:  -1,
    email: ''
  }

  constructor(private _snackBar: MatSnackBar,
              private activatedRoute: ActivatedRoute,private router: Router,  private http : HttpClient) {

  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.model.id = params['id'];
      this.model.email = params['email'];

      let url = "https://eclinic05.herokuapp.com/patients/activate"
      this.http.post(url,this.model).subscribe(
        res => {
          this._snackBar.open("Your account is successfully activated!", "Close", {
            duration: 2000,
          });
          let booleanPromise = this.router.navigate(['../'], { relativeTo: this.activatedRoute });


        },
        err => {

          this._snackBar.open("Error has occurred while activating your account!", "Close", {
            duration: 2000,
          });
        }
      );

    });

  }
}

export interface activateModel{
  email: String;
  id : number;
}
