import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-add-admin-clinic-form',
    templateUrl: './add-admin-clinic-form.component.html',
    styleUrls: ['./add-admin-clinic-form.component.css']
})
export class AddAdminClinicFromComponent implements OnInit{

    model: adminClinicModel = {
        name: '',
        surname: '',
        username: '',
        password: ''
    }

    constructor(private http: HttpClient){

    }

    ngOnInit(): void{

    }

    AddAdminClinic(): void{
        let url = "http://localhost:8080/adminClinic/addAdminClinic"
        this.http.post(url, this.model).subscribe(
            res => {
                alert("Clinic admin added successfully");
                location.reload();
            },
            err => {
                alert("Error has occured while adding clinic admin");
                console.log(err);
            } 
        );
    
    }
}

export interface adminClinicModel
{
    name: string | RegExp;
    surname: string | RegExp;
    username: string;
    password: string;
}