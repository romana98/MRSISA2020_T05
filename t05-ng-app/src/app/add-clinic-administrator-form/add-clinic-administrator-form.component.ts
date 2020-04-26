import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";


@Component({
    selector: 'app-add-admin-form',
    templateUrl: './add-clinic-administrator-form.component.html',
    styleUrls: ['./add-clinic-administrator-form.component.css']
})
export class AddClinicAdminFromComponent implements OnInit{

    model: clinicAdminModel = {
        name: '',
        surname: '',
        email: '',
        password: '',
        clinic: new class implements clinicModel {
          address: string;
          description: string;
          name: string | RegExp;
        }
    }
    clinics: any=[];

    constructor(private http: HttpClient){

    }


    ngOnInit(): void{
         this.http.get("http://localhost:8081/clinic/getClinics")
          .subscribe((res)=>{
            this.clinics = res;
            this.model.clinic = res[0];
        });

    }

    AddClinicAdmin(): void{
        let url = "http://localhost:8081/clinicAdministrator/addClinicAdministrator"
        this.http.post(url, this.model).subscribe(
            res => {
                alert("Admin added successfully");
                location.reload();
            },
            err => {
                alert("Error has occured while adding admin");
                console.log(err);
            }
        );

    }
}

export interface clinicAdminModel
{
    name: string | RegExp;
    surname: string | RegExp;
    email: string;
    password: string;
    clinic: clinicModel;
}
