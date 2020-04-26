import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-add-admin-clinic-form',
    templateUrl: './add-clinic-center-administrator-form.component.html',
    styleUrls: ['./add-clinic-center-administrator-form.component.css']
})
export class AddClinicCenterAdminFromComponent implements OnInit{

    model: clinicCenterAdminModel = {
        name: '',
        surname: '',
        email: '',
        password: ''
    }

    constructor(private http: HttpClient){

    }

    ngOnInit(): void{

    }

    AddClinicCenterAdmin(): void{
        let url = "http://localhost:8081/clinicCenterAdministrator/addClinicCenterAdministrator"
        this.http.post(url, this.model).subscribe(
            res => {
                alert("Clinic center administrator added successfully");
                location.reload();
            },
            err => {
                alert("Error has occured while adding clinic center administrator!");
                console.log(err);
            } 
        );
    
    }
}

export interface clinicCenterAdminModel
{
    name: string | RegExp;
    surname: string | RegExp;
    email: string;
    password: string;
}