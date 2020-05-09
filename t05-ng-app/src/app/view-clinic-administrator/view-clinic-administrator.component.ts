import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-view-clinic-administrator',
  templateUrl: './view-clinic-administrator.component.html',
  styleUrls: ['./view-clinic-administrator.component.css']
})
export class ViewClinicAdministratorComponent implements OnInit{

  model: clinicAdminModel = {
    name: '',
    surname: '',
    email: '',
    password: '',
    clinic: new class implements clinicModel {
      address: '';
      description: '';
      name: '';
    }
  }
  hide: boolean;
  clinic_info : string;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: Router, private r:ActivatedRoute){

  }

  ngOnInit(): void{

    this.http.get("http://localhost:8081/clinicAdministrator/getClinicAdministrator")
      .subscribe((res)=>{
        this.model = <clinicAdminModel>res;
        this.clinic_info = this.model.clinic.name + ", " + this.model.clinic.address;
      });
    this.hide = true;

  }

  goToEdit(): void {
    let booleanPromise = this.router.navigate(["../editProfile"], {relativeTo: this.r});
  }


}


export interface clinicAdminModel
{
  name: string | RegExp;
  surname: string | RegExp;
  email: string | RegExp;
  password: string;
  clinic: clinicModel;
}
