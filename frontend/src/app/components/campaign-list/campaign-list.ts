import { Component, OnInit, ChangeDetectorRef, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService, Campaign, EmeraldAccount } from '../../services/api';

@Component({
  selector: 'app-campaign-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './campaign-list.html'
})
export class CampaignListComponent implements OnInit {
  @Output() editClicked = new EventEmitter<Campaign>();
  campaigns: Campaign[] = [];
  account: EmeraldAccount | null = null;

  constructor(
    private apiService: ApiService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.apiService.getAccount().subscribe({
      next: (acc) => {
        this.account = acc;
        this.cdr.detectChanges();
      }
    });

    this.apiService.getCampaigns().subscribe({
      next: (data) => {
        this.campaigns = data;
        this.cdr.detectChanges();
      }
    });
  }

  onEdit(campaign: Campaign) {
    this.editClicked.emit(campaign);
  }

  deleteCampaign(id: number | undefined) {
    if (id) {
      this.apiService.deleteCampaign(id).subscribe(() => {
        this.loadData();
      });
    }
  }
}
