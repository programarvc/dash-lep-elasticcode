import { cognito } from 'src/environments/environment';

const awsconfig = {
  'userPoolId': cognito.userPoolId,
  'userPoolWebClientId': cognito.userPoolWebClientId
}

export default awsconfig;