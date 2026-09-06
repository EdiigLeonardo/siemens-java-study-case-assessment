export type LeaveType = 'VACATION' | 'SICK' | 'UNPAID';
export type LeaveStatus = 'PENDING' | 'APPROVED' | 'REJECTED' | 'CANCELLED';

export interface LeaveRequest {
  id: number;
  employeeId: number;
  startDate: string;
  endDate: string;
  type: LeaveType;
  status: LeaveStatus;
  reason: string;
}
