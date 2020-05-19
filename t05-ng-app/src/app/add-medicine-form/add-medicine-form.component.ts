import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-medicine-form',
  templateUrl: './add-medicine-form.component.html',
  styleUrls: ['./add-medicine-form.component.css']
})
export class AddMedicineFromComponent implements OnInit{

  model: medicineModel = {
    name: '',
    description: '',
    authenticated: false
  }
  buttonType: string;

  ngOnInit(): void{

  }

  constructor(private _snackBar: MatSnackBar, private http: HttpClient, private router: Router){

  }

  AddMedicine(): void{
    let url = "/medicine/addMedicine"
    this.http.post(url, this.model).subscribe(
      res => {
        this._snackBar.open("Medicine added successfully!", "Close", {
          duration: 2000,
        });

      },
      err => {
        if(err.status == 409)
        {
          this._snackBar.open("Medicine already exists!", "Close", {
            duration: 2000,
          });

        }
        else
        {
          this._snackBar.open("Error has occurred while adding medicine!", "Close", {
            duration: 2000,
          });
          console.log(err);
        }
      }
    );

  }

  onSubmit(buttonType): void {

    if(buttonType==="Add"){
      this.AddMedicine();
    }
    if(buttonType==="Leave"){
      this.AddMedicine();
      this.router.navigateByUrl('/');
    }

  }
}

export interface medicineModel
{
  name: string | RegExp;
  description: string;
  authenticated: boolean;
}
