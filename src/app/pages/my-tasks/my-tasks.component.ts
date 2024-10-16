import {Component, OnInit} from '@angular/core';
import {ContactDto} from "../../services/models/contact-dto";
import {ContactService} from "../../services/services/contact.service";
import {HelperService} from "../../services/helper/helper.service";
import {Router, RouterLink} from "@angular/router";
import {TaskDto} from "../../services/models/task-dto";
import {TaskService} from "../../services/services/task.service";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-my-tasks',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './my-tasks.component.html',
  styleUrl: './my-tasks.component.scss'
})
export class MyTasksComponent implements OnInit{
  tasks: Array<TaskDto> = [];
  constructor(
    private taskService: TaskService,
    private helperService: HelperService,
    private router: Router
  ) {}
  ngOnInit(): void {

  }

  private findAllTaskByUser() {
    this.taskService.findAllByUserId1({
      "user-id": this.helperService.userId
    }).subscribe({
      next: (data) => {
        this.tasks = data;

      }
    });
  }





}
