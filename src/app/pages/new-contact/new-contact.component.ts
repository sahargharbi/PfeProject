import {Component, OnInit} from '@angular/core';
import {ContactDto} from "../../services/models/contact-dto";
import {ContactService} from "../../services/services/contact.service";
import {HelperService} from "../../services/helper/helper.service";
import {FormsModule} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-new-contact',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './new-contact.component.html',
  styleUrl: './new-contact.component.scss'
})
export class NewContactComponent implements OnInit {
  contact: ContactDto={};
  errorMessages: Array<string>= [];

  constructor(
    private contactService: ContactService,
    private helperService: HelperService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }
  ngOnInit(): void {
    const contactId= this.activatedRoute.snapshot.params['idContact'];
    if (contactId) {
      this.contactService.findById3({
        'contact-id': this.activatedRoute.snapshot.params['idContact']
      }).subscribe({
        next:(data)=>{
          this.contact=data;
        }
      });
    }
  }

  save(){
    this.errorMessages= [];
    this.contact.userId=this.helperService.userId;
    this.contactService.save3({
      body: this.contact
    }).subscribe({
      next:async (data) => {
        await this.router.navigate(['user/my-contact-list']);

      },
      error:(err) => {
        this.errorMessages= err.error.validationMessage;
      }
    });
  }

  async cancel() {
    await this.router.navigate(['user/my-contact-list']);

  }
}
