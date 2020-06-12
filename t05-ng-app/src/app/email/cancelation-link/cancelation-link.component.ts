import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-cancelation-link',
  templateUrl: './cancelation-link.component.html',
  styleUrls: ['./cancelation-link.component.css']
})
export class CancelationLinkComponent implements OnInit {

  constructor(private _snackBar: MatSnackBar,
              private activatedRoute: ActivatedRoute,private router: Router,  private http : HttpClient) {

  }

  id:string = '';

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      this.id = params['appointment_id'];

      let url = "http://localhost:8081/appointment/cancel"
      this.http.post(url,this.id).subscribe(
        res => {
          this._snackBar.open("Your appointment is successfully canceled!", "Close", {
            duration: 2000,
          });
          let booleanPromise = this.router.navigate(['../'], { relativeTo: this.activatedRoute });


        },
        err => {
          if (err.status == 409) {
            this._snackBar.open("Can't cancel less appointment then 24h before starting time!", "Close", {
              duration: 2000,
            });
          } else {
            this._snackBar.open("Error has occurred while canceling your appointment!", "Close", {
              duration: 2000,
            });
          }
        }
      );

    });

  }

}
