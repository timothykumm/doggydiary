import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/api/auth/auth.service';
import { UserService } from 'src/app/services/api/user/user.service';
import { JwtService } from 'src/app/services/utils/jwt/jwt.service';
import { LoginService } from 'src/app/services/utils/login/login.service';

@Component({
  selector: 'ddr-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit{

  activeTab = 'general';
  dropdownToggled = false;
  languageModels = ['gpt-3.5-turbo-16k', 'gpt-3.5-turbo', 'bard', 'gpt-4'];
  selectedLanguageModel = 0;

  settings = {
    openai: '',
    email: ''
  }


  constructor(private userService: UserService, private jwtService: JwtService, private loginService: LoginService) { }

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()) {
    this.settings.openai = this.jwtService.extractClaim('openai');
    this.settings.email = this.jwtService.extractClaim('sub');
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
    this.selectedLanguageModel = index;
  }

  getSelectedLanguageModel() {
    return this.languageModels[this.selectedLanguageModel]
  }

  isLanguageModelActive(index: number) {
    return index === this.selectedLanguageModel;
  }

  isLanguageModelDisabled(index: number) {
    return index === 2 || index === 3; //bard and chatgpt4
  }

  editApikey() {
    this.editApikeyApi(this.settings.openai).then(async () => {
      console.log("Changed Api key")
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
