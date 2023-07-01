import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/api/user/user.service';
import { LoginService } from 'src/app/services/utils/login/login.service';
import { SettingsService } from 'src/app/services/utils/settings/settings.service';

@Component({
  selector: 'ddr-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit{

  activeTab = 'general';
  dropdownToggled = false;
  languageModels = [''];
  selectedLanguageModelIndex = 0;

  settings = {
    openai: '',
    email: ''
  }

  constructor(private userService: UserService, private loginService: LoginService, private settingsService: SettingsService) { }

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()) {
    this.languageModels = this.settingsService.getLanguageModelList();

    this.settings.openai = this.settingsService.getOpenai();
    this.settings.email = this.settingsService.getEmail();
    this.selectedLanguageModelIndex = this.languageModels.indexOf(this.settingsService.getLanguageModel());
    }
  }

  switchTab(tab: string) {
    this.activeTab = tab;
  }

  isTabActive(tab: string) {
    return this.activeTab === tab;
  }

  isDropdownToggled() {
    return this.dropdownToggled;
  }

  toggleDropdown() {
    this.dropdownToggled = !this.dropdownToggled;
  }

  selectLanguageModel(index: number) {
    this.selectedLanguageModelIndex = index;
  }

  getSelectedLanguageModel() {
    return this.languageModels[this.selectedLanguageModelIndex]
  }

  isLanguageModelActive(index: number) {
    return index === this.selectedLanguageModelIndex;
  }

  isLanguageModelDisabled(index: number) {
    return index === 2 || index === 3; //bard and chatgpt4
  }

  update() {
    this.editApikeyApi(this.settings.openai).then(async () => {
      console.log("Changed Api key")
      this.settingsService.setOpenai(this.settings.openai);
      this.settingsService.setLanguageModel(this.getSelectedLanguageModel());
      console.log(this.getSelectedLanguageModel())
    }).catch((e) => console.log(e));
  }

  async editApikeyApi(apikey: string): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      this.userService.editApikey(apikey).subscribe({
        next: (r) => { resolve(r); },
        error: (e) => { reject(e); }
      });
    });
  }

}
