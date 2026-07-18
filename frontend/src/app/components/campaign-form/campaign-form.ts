import { Component, EventEmitter, Output, ViewChild, ChangeDetectorRef } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ApiService, Campaign } from '../../services/api';

@Component({
  selector: 'app-campaign-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './campaign-form.html'
})
export class CampaignFormComponent {
  @Output() campaignAdded = new EventEmitter<void>();

  @ViewChild('form') form!: NgForm;

  campaign: Campaign = this.getEmptyCampaign();
  towns = ['Kraków', 'Warsaw', 'Gdańsk', 'Wrocław', 'Poznań'];
  errorMessage = '';
  isEditMode = false;

  constructor(
    private apiService: ApiService,
    private cdr: ChangeDetectorRef
  ) {}

  startEdit(campaign: Campaign) {
    this.campaign = { ...campaign };
    this.isEditMode = true;
    this.errorMessage = '';
    this.cdr.detectChanges();
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  cancelEdit() {
      this.isEditMode = false;
      this.errorMessage = '';
      this.campaign = this.getEmptyCampaign();
      this.form.resetForm(this.campaign);
      this.cdr.detectChanges();
    }

  submitForm() {
    if (this.isEditMode && this.campaign.id) {
      this.apiService.updateCampaign(this.campaign.id, this.campaign).subscribe({
        next: () => this.handleSuccess(),
        error: (err) => this.handleError(err)
      });
    } else {
      this.apiService.createCampaign(this.campaign).subscribe({
        next: () => this.handleSuccess(),
        error: (err) => this.handleError(err)
      });
    }
  }

  private handleSuccess() {
      this.errorMessage = '';
      this.isEditMode = false;
      this.campaign = this.getEmptyCampaign();
      this.form.resetForm(this.campaign);
      this.campaignAdded.emit();
      this.cdr.detectChanges();
    }

  private handleError(err: any) {
    if (err.error && err.error.error) {
      this.errorMessage = err.error.error;
    } else if (err.error) {
      this.errorMessage = Object.values(err.error).join(', ');
    } else {
      this.errorMessage = 'An unexpected error occurred.';
    }
    this.cdr.detectChanges();
  }

  private getEmptyCampaign(): Campaign {
    return { name: '', keywords: '', bidAmount: 0.1, campaignFund: 100, status: true, town: 'Kraków', radius: 10 };
  }
}
