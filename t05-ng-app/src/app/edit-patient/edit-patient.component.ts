import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-edit-patient',
    templateUrl: './edit-patient.component.html',
    styleUrls: ['./edit-patient.component.css']
})
export class EditPatientProfile implements OnInit{

    model: patientModel = {
        email : 'email1@email.com',
        password : 'password1',
        first_name : 'Ime1',
        last_name : 'Prezime1',
        address : 'Adresa1',
        city : 'Grad1',
        country : 'Drzava1',
        phone_number : 'Telefon1',
        unique_num : 'Jedinstveni1'
    }

    constructor(private http: HttpClient){

    }

    ngOnInit(): void{

    }

    editPatient(): void{
        let url = "http://localhost:8081/patients/editPatient"
        this.http.post(url,this.model).subscribe(
            res => {
                alert("Your profile has been updated successfully!");
                location.reload();
            },
            err => {
                alert("Error has occured while updating your profile!");
                console.log(err)
            }
        );
    }
}
    export interface patientModel{
        email : string;
        password : string;
        first_name : string;
        last_name : string;
        address : string;
        city : string;
        country : string;
        phone_number : string;
        unique_num : string;
    }

