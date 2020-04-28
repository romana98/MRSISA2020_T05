import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-edit-medical-staff',
    templateUrl: './edit-medical-staff.component.html',
    styleUrls: ['./edit-medical-staff.component.css']
})
export class EditMedicalStaff implements OnInit{

    model: medicalStaffModel = {
        email : 'email1@email.com',
        password : 'password1',
        name : 'Ime1',
        surname : 'Prezime1',
        type : 'doctor'
    }

    constructor(private http: HttpClient){

    }

    ngOnInit(): void{

    }

    editMedicalStaff(): void{
        let url = "http://localhost:8081/medicalStaff/editMedicalStaff"
        this.http.post(url,this.model).subscribe(
            res => {
                alert("Your profile has been updated successfully!");

            },
            err => {
                alert("Error has occured while updating your profile!");
                console.log(err)
            }
        );
    }
}
    export interface medicalStaffModel{
        email : string;
        password : string;
        name : string;
        surname : string;
        type : string;
    }

