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

export interface AllStories {
  epic: string;
  name: string;
  story_count: number;
}

export interface MediaStoriesPerEpic {
  media_stories: number;
}

export interface StoriesAndEpicsData {
    average_points: number;
    total_points: number;
    total_stories: number;
}

export interface TotalPoints {
  total_points: number;

}

export interface JiraEpics {
  id: number;
  epic: string;
  name: string;
  count_epics: number;
}
