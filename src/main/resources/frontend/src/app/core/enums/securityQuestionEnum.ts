




export enum SecurityQuestionEnum {
  ANIMAL = 'Jakie jest Twoje ulubione zwierzę?',
  FRIENDS_NAME = 'Jakie jest imię Twojego przyjaciela z dzieciństwa?',
  SANDWICH = 'Z czym najbardziej lubisz kanapki?'
}


export const securityQuestions = [
  {
    key: Object.keys(SecurityQuestionEnum)[0],
    value: SecurityQuestionEnum.ANIMAL
  },
  {
    key: Object.keys(SecurityQuestionEnum)[1],
    value: SecurityQuestionEnum.FRIENDS_NAME
  },
  {
    key: Object.keys(SecurityQuestionEnum)[2],
    value: SecurityQuestionEnum.SANDWICH
  }
]
