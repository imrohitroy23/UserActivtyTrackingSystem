export interface UserActivity {
  id: number;
  browserName: string;
  osName: string;
  ipAddress: string;
  loginTime: Date;
  logoutTime: Date;
  sessionDuration: number;
}
