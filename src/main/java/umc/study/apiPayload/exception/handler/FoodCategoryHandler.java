package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.FoodCategoryRepository;

public class FoodCategoryHandler extends RuntimeException {
  private final String errorcode;;
  private final String errorMessage;

  public FoodCategoryHandler(ErrorStatus errorStatus){
    super(errorStatus.toString());
    this.errorcode = errorStatus.toString();
    this.errorMessage = errorStatus.toString();
  }

  public String getErrorcode() {
    return errorcode;
  }
  public String getErrorMessage() {
    return errorMessage;
  }
}
