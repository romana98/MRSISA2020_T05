import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-add-clinic-form',
    templateUrl: './add-clinic-form.component.html',
    styleUrls: ['./add-clinic-form.component.css']
})
export class AddClinicFromComponent implements OnInit{

    model: clinicModel = {
        name: '',
        address: '',
        description: ''
    }

    ngOnInit(): void{

    }


    constructor(private _snackBar: MatSnackBar, private http: HttpClient){

    }

    AddClinic(): void{
        let url = "http://localhost:8081/clinic/addClinic"
        this.http.post(url, this.model).subscribe(
            res => {
              this._snackBar.open("Clinic added successfully", "Close", {
                duration: 2000,
              });

            },
            err => {
              if(err.status == 409)
              {
                this._snackBar.open("Clinic already exists", "Close", {
                  duration: 2000,
                });

              }
              else
              {
                this._snackBar.open("Error has occurred while adding clinic", "Close", {
                  duration: 2000,
                });

                console.log(err);
              }
            }
        );

    }
}

export interface clinicModel
{
    name: string | RegExp;
    address: string | RegExp;
    description: string;
}
