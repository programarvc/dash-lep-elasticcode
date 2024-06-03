export interface JiraActivities {
  id: number;
  epic: string;
  parent: string;
  name: string;
  priority: string;
  sprint: string;
  typeDetail: string;
  statusDetail: string; 
  story_count: number;
}

export interface JiraEpics {
  id: number;
  epic: string;
  name: string;
  count_epics: number;
}