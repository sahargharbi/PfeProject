import {Component, OnInit} from '@angular/core';
import {InformationComponent, LightInfoInput} from "../../components/information/information.component";
import {DatePipe, DecimalPipe, NgClass, NgForOf} from "@angular/common";
import {StatisticsService} from "../../services/services/statistics.service";
import {HelperService} from "../../services/helper/helper.service";
import {lastValueFrom} from "rxjs";
import {Chart, registerables, ChartType, ChartDataset} from "chart.js";
import {NgChartsModule} from "ng2-charts";
import {DatepickerModule,DatepickerOptions} from "ng2-datepicker";
import {FormsModule} from "@angular/forms";
Chart.register(...registerables)


@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [
    InformationComponent,
    NgForOf,
    NgChartsModule,
    FormsModule,
    NgClass,
    DecimalPipe
  ],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.scss'
})
export class UserDashboardComponent implements OnInit {

  accountInfoList: Array<LightInfoInput> = [];
  private accountBalance= 0;
  private highestTransfer= 0;
  private highestDeposit= 0;
  chartType: ChartType= 'line';
  dataset: ChartDataset[]=[];
  labels: string[]=[];
  chartOptions: any={
    legend:{
      position:'bottom',
      labels:{
        fontsize:16,
        usePointStyle:true
      }
    }
  };
  dateOptions: DatepickerOptions = {
    format: 'yyyy-MM-dd'
  };
  startDate: Date= new Date();

  endDate: Date= new Date();



  constructor(
    private statisticsService: StatisticsService,
    private helperService: HelperService,
    private datePipe: DatePipe
  ) {}
  ngOnInit() {
    this.initializeAccountInfo();
  }


  /*getData(){
    console.log(this.startDate)
    this.statisticsService.findTransactionByDate({
      'user-id': this.helperService.userId,
      'start-date': this.datePipe.transform(this.startDate,'yyyy-MM-dd') as string,
      'end-date': this.datePipe.transform(this.endDate,'yyyy-MM-dd') as string

    }).subscribe({
      next:(values)=>{
        console.log(values);
        this.dataset=[];
        this.labels=[];
        const chartDataSet: ChartDataset<'line'> ={
          data: [],
          label:'transactions by date',
          tension:0.4,
          fill: true

        };
        const dataValues: Array<number>=[];
        for(let record of values){
          this.labels.push(record.transactionDate as string);
          dataValues.push(record.amount as number);
        }
        chartDataSet.data=dataValues;
        this.dataset.push(chartDataSet);
      }
    });
  }*/

  private async initializeAccountInfo() {

    this.accountBalance= await lastValueFrom(
      this.statisticsService.getAccountBalance({'user-id': this.helperService.userId})
    );
    this.highestTransfer= await lastValueFrom(
      this.statisticsService.highestTransfert({
        'user-id':this.helperService.userId
      })
    );
    this.highestDeposit= await lastValueFrom(
      this.statisticsService.highestDeposit({
        'user-id':this.helperService.userId
      })
    );


    this.accountInfoList = [
      {
        title: 'Account Balance',
        amount: this.accountBalance,
        infoStyle: 'bg-primary'
      },

      {
        title: 'Highest Transfer',
        amount: this.highestTransfer,
        infoStyle: 'bg-warning'
      },

      {
        title: 'Highest Deposit',
        amount: this.highestDeposit,
        infoStyle: 'bg-success'
      }
      ];

  }

}
