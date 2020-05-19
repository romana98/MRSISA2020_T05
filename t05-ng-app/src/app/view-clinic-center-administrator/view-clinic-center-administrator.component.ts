import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-view-clinic-center-administrator',
  templateUrl: './view-clinic-center-administrator.component.html',
  styleUrls: ['./view-clinic-center-administrator.component.css']
})
export class ViewClinicCenterAdministratorComponent implements OnInit{

  model: clinicCenterAdminModel = {
    name: '',
    surname: '',
    email: '',
    password: '',

  }
  hide: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: Router, private r:ActivatedRoute){

  }

  ngOnInit(): void{

    this.http.get("/clinicCenterAdministrator/getClinicCenterAdministrator")
      .subscribe((res)=>{
        this.model = <clinicCenterAdminModel>res;
      });
    this.hide = true;

  }

  goToEdit(): void {
    let booleanPromise = this.router.navigate(["../editProfile"], {relativeTo: this.r});
  }


}


export interface clinicCenterAdminModel
{
  name: string | RegExp;
  surname: string | RegExp;
  email: string | RegExp;
  password: string;
}
