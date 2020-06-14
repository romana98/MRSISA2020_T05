import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-diagnosis-form',
  templateUrl: './add-diagnosis-form.component.html',
  styleUrls: ['./add-diagnosis-form.component.css']
})
export class AddDiagnosisFormComponent implements OnInit{

  model: diagnosisModel = {
    name: '',
    description: ''
  }
  buttonType: string;

  ngOnInit(): void{

  }

  constructor(private _snackBar: MatSnackBar, private http: HttpClient, private router: Router){

  }

  AddDiagnosis(): void{
    let url = "https://eclinic05.herokuapp.com/diagnosis/addDiagnosis"
    this.http.post(url, this.model).subscribe(
      res => {

        this._snackBar.open("Diagnosis added successfully!", "Close", {
          duration: 2000,
        });

      },
      err => {
        if(err.status == 409)
        {
          this._snackBar.open("Diagnosis already exists!", "Close", {
            duration: 2000,
          });

        }
        else
        {
          this._snackBar.open("Error has occurred while adding diagnosis!", "Close", {
            duration: 2000,
          });
          console.log(err);
        }
      }
    );

  }

  onSubmit(buttonType): void {
    if(buttonType==="Add"){
      this.AddDiagnosis();
    }
    if(buttonType==="Leave"){
      this.AddDiagnosis();
      this.router.navigateByUrl('/clinicCenterAdmin/addClinicCenterAdministrator');
    }

  }
}

export interface diagnosisModel
{
  name: string | RegExp;
  description: string | RegExp;

}
