package uz.result.primemedicalcentre.documentation;

public interface SwaggerConstants {

    String SERVICE_UPDATE_DESCRIPTION = """
             UpdateDTO containing the updated information of the Service - UpdateDTO, содержащий обновленную информацию об Услуге
            \s""";

    String SERVICE_FULL_FORM = """
            {
              "id": 0,
              "name": {
                "uz": "string",
                "ru": "string"
              },
              "description": {
                "uz": "string",
                "ru": "string"
              },
              "active": true
            }
            \s""";
    String SERVICE_CUSTOM_FIELD = """
            {
              "id": 0,
              "name": {
                "uz": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            \s""";

    String DOCTOR_FULL_FORM = """
            {
              "id": 0,
              "fullName": {
                "uz": "string",
                "ru": "string"
              },
              "description": {
                "uz": "string",
                "ru": "string"
              },
              "experience": {
                "uz": "string",
                "ru": "string"
              },
              "receptionTime": {
                "uz": "string",
                "ru": "string"
              },
              "active": true,
              "specializationList": [
                {
                  "id": 0,
                  "name": {
                    "uz": "string",
                    "ru": "string"
                  }
                }
              ],
              "educationList": [
                {
                  "id": 0,
                  "startYear": "string",
                  "finishYear": "string",
                  "institution": {
                    "uz": "string",
                    "ru": "string"
                  },
                  "qualification": {
                    "uz": "string",
                    "ru": "string"
                  }
                }
              ]
            }           \s
            """;
    String DOCTOR_CUSTOM_FIELD = """
            {
              "id": 0,
              "fullName": {
                "uz": "string"
              }
            },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"           \s
            """;
    String DOCTOR_UPDATE_DESCRIPTION = """
            Send which field must edit.
             1) If you need add new 'specialization' Please send 'specialization' without 'id' filed and it added
             If you need remove 'specialization' Please send only 'id' filed and it deleted
             2) If you need add new 'education' Please send 'education' without 'id' filed and it added
             If you need remove 'education' Please send only 'id' filed and it deleted
                                        \s
             Отправьте, какое поле необходимо редактировать
             1) Если вам нужно добавить новый 'specialization', пожалуйста, отправьте 'specialization' без поля 'id', и он будет добавлен
             Если вам нужно удалить 'specialization', пожалуйста, отправьте только поле 'id', и он будет удален
             2) Если вам нужно добавить новый 'education', пожалуйста, отправьте 'education' без поля 'id', и он будет добавлен
             Если вам нужно удалить 'education', пожалуйста, отправьте только поле 'id', и он будет удален
                       \s
            """;
    String ADD_SPECIALIZATION_DESC = """
            Send 'specialization' without 'id' and it added, you can add a lot 'specialization' at the same time
                        
            Отправьте «specialization» без «id» и он будет добавлен, вы можете добавить много «specialization» одновременно
            """;
    String ADD_SPECIALIZATION_JSON = """
            {
              "id": 0,
              "specializationList": [
                {
                  "name": {
                    "uz": "string",
                    "ru": "string"
                  }
                }
              ],
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;
    String DELETE_SPECIALIZATION_DESC = """
            send only 'specialization' and 'id' and it deleted, you can delete a lot 'specialization' at the same time
                        
            отправьте только «specialization» и «id», и они будут удалены, вы можете удалить много «specialization» одновременно
            """;
    String DELETE_SPECIALIZATION_JSON = """
            {
              "id": 0,
              "specializationList": [
                {
                  "id": 0
                }
              ],
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;
    String ADD_EDUCATION_DESC = """
            send 'education' without 'id' and it added, you can add a lot 'education' at the same time
                        
            отправьте «education» без «id» и он будет добавлен, вы можете добавить много «education» одновременно
            """;
    String ADD_EDUCATION_JSON = """
            {
              "id": 0,
              "educationList": [
                {
                  "startYear": "string",
                  "finishYear": "string",
                  "institution": {
                    "uz": "string",
                    "ru": "string"
                  },
                  "qualification": {
                    "uz": "string",
                    "ru": "string"
                  }
                }
              ],
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;
    String DELETE_EDUCATION_DESC = """
            send only 'education' and 'id' and it deleted, you can delete a lot 'education' at the same time
                        
            отправьте только «education» и «id», и они будут удалены, вы можете удалить много «education» одновременно
            """;
    String DELETE_EDUCATION_JSON = """
            {
              "id": 0,
              "educationList": [
                {
                  "id": 0
                }
              ],
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;
    String SERVICE_OF_DOCTOR_FULL_FORM = """
            {
              "id": 0,
              "name": {
                "uz": "string",
                "ru": "string"
              },
              "price": {
                "uz": "string",
                "ru": "string"
              },
              "active": true
            }
            """;
    String SERVICE_OF_DOCTOR_CUSTOM_FIELD = """
            {
              "id": 0,
              "name": {
                "uz": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;

    String NEWNESS_UPDATE_DESCRIPTION = """
             Send which field must edit.
             1) If you need add new 'option' Please send 'option' without 'id' filed and it added
             If you need remove 'option' Please send only 'id' filed and it deleted
                                      \s
             Отправьте, какое поле необходимо редактировать
             1) Если вам нужно добавить новый 'option', пожалуйста, отправьте 'option' без поля 'id', и он будет добавлен
             Если вам нужно удалить 'option', пожалуйста, отправьте только поле 'id', и он будет удален
            """;

    String ADD_OPTION_DESC = """
             send 'option' without 'id' and it added, you can add a lot 'option' at the same time
                        \s
             отправьте «option» без «id» и он будет добавлен, вы можете добавить много «option» одновременно
            \s""";

    String ADD_PHOTO_TO_OPTION_DESC = """                        
            If you want to add a photo to an option that was previously saved without a photo during the update process,
             you must first save the photo and then send only the URL of the photo stored in the database in JSON format.
                                                
            Если вы хотите добавить фото в опцию, которая ранее была сохранена без фото во время процесса обновления,
             сначала нужно сохранить фото, а затем отправить только URL фото, сохранённого в базе данных, в формате JSON.
            """;

    String ADD_PHOTO_TO_OPTION_JSON = """
            {
              "id": 0,
              "optionList": [
                {
                  "photo": {
                    "url": "photo_url"
                  }
                },
              "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
              ],
              "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;

    String DELETE_OPTION_DESC = """
            send only 'option' and 'id' and it deleted, you can delete a lot 'option' at the same time
                        
            отправьте только «option» и «id», и они будут удалены, вы можете удалить много «option» одновременно
            """;
    String NEWNESS_FULL_FORM = """
            {
              "id": 0,
              "createdDate": {
                "uz": "string",
                "ru": "string"
              },
              "active": true,
              "optionList": [
                {
                  "id": 0,
                  "title": {
                    "uz": "string",
                    "ru": "string"
                  },
                  "body": {
                    "uz": "string",
                    "ru": "string"
                  }
                }
              ]
            }           \s
            """;
    String CUSTOM_FIELD_NEWNESS = """
            {
              "id": 0,
              "createdDate": {
                "uz": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }          \s
            """;
    String ADD_OPTION_JSON = """
            {
              "id": 0,
              "optionList": [
                {
                  "title": {
                    "uz": "string",
                    "ru": "string"
                  },
                  "body": {
                    "uz": "string",
                    "ru": "string"
                  }
                }
              ],
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }            \s
            """;
    String DELETE_OPTION_JSON = """
            {
              "id": 0,
              "optionList": [
                {
                  "id": 0
                }
              ],
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }           \s
            """;
    String ABOUT_US_BANNER_UPDATE_DESCRIPTION = """
            UpdateDTO containing the updated information of the "AboutUsBanner" - UpdateDTO, содержащий обновленную информацию об "AboutUsBanner"
            """;
    String ABOUT_US_BANNER_FULL_FORM = """
            {
              "id": 0,
              "title": {
                "uz": "string",
                "ru": "string"
              },
              "body": {
                "uz": "string",
                "ru": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }           \s
            """;
    String ABOUT_US_BANNER_CUSTOM_FIELD = """
            {
              "id": 0,
              "title": {
                "uz": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }           \s
            """;
    String ABOUT_US_SERVICE_UPDATE_DESCRIPTION = """
            UpdateDTO containing the updated information of the "AboutUsService" - UpdateDTO, содержащий обновленную информацию об "AboutUsService"
            """;
    String ABOUT_US_SERVICE_FULL_FORM = """
            {
              "id": 0,
              "title": {
                "uz": "string",
                "ru": "string"
              },
              "body": {
                "uz": "string",
                "ru": "string"
              }
            }
            """;
    String ABOUT_US_SERVICE_CUSTOM_FIELD = """
            {
              "id": 0,
              "title": {
                "uz": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;
    String ABOUT_US_LABORATORY_UPDATE_DESCRIPTION = """
            UpdateDTO containing the updated information of the "AboutUsLaboratory" - UpdateDTO, содержащий обновленную информацию об "AboutUsLaboratory"
            """;
    String ABOUT_US_LABORATORY_FULL_FORM = """
            {
              "id": 0,
              "title": {
                "uz": "string",
                "ru": "string"
              },
              "body": {
                "uz": "string",
                "ru": "string"
              }
            }
            """;
    String ABOUT_US_LABORATORY_CUSTOM_FIELD = """
            {
              "id": 0,
              "title": {
                "uz": "string"
              },
            "other_field_to_edit_if_need": "другое поле для редактирования (если нужно)"
            }
            """;
}
