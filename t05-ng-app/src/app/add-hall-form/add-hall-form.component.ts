import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-add-hall-form',
    templateUrl: './add-hall-form.component.html',
    styleUrls: ['./add-hall-form.component.css']
})
export class AddHallFormComponent implements OnInit{

    model: hallModel = {
        name: '',
        number: 0,
        admin: 'emailadmin@admin.com'
    }

    constructor(private _snackBar: MatSnackBar, private http: HttpClient){

    }


    ngOnInit(): void{


    }

    AddHall(): void{
        let url = "http://localhost:8081/halls/addHall"
        this.http.post(url, this.model).subscribe(
            res => {
              this._snackBar.open("Hall added successfully!", "Close", {
                duration: 2000,
              });

            },
          err => {
            if(err.status == 409)
            {
              this._snackBar.open("Hall name/number already taken!", "Close", {
                duration: 2000,
              });

            }
            else
            {
              this._snackBar.open("Error has occurred while adding hall!", "Close", {
                duration: 2000,
              });

            }
          }
        );

    }
}

export interface hallModel
{
    name: string | RegExp;
    number: Number | RegExp;
    admin: string | RegExp;
}
