import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-add-hall-form',
    templateUrl: './add-hall-form.component.html',
    styleUrls: ['./add-hall-form.component.css']
})
export class AddHallFormComponent implements OnInit{

    model: hallModel = {
        name: '',
        number: -1,
        admin: 'emailadmin@admin.com'
    }

    constructor(private http: HttpClient){

    }


    ngOnInit(): void{


    }

    AddHall(): void{
        let url = "http://localhost:8081/halls/addHall"
        this.http.post(url, this.model).subscribe(
            res => {
                alert("Hall added successfully");

            },
            err => {
                alert("Error has occurred while adding hall");
                console.log(err);
            }
        );

    }
}

export interface hallModel
{
    name: string | RegExp;
    number: Number | RegExp;
    admin: string;
}
