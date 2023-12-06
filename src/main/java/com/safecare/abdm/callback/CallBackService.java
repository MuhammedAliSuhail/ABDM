package com.safecare.abdm.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.safecare.abdm.transaction.ABDMTransaction;
import com.safecare.abdm.transaction.ABDMTransactionRepo;

@Service

public class CallBackService {
	@Autowired
	ABDMTransactionRepo abdmTransactionRepo;
	Gson json = new Gson();

	public void onFetchMode(OnFetchModeModel onFetchModeModel) {
		ABDMTransaction abdmTransaction = abdmTransactionRepo
				.findByRequestId(onFetchModeModel.getResp().getRequestId());

		abdmTransaction.setResponseBody(json.toJson(onFetchModeModel));

		abdmTransactionRepo.save(abdmTransaction);
	}

	public void onInit(OnInitModel onInitModel) {
		ABDMTransaction abdmTransaction = abdmTransactionRepo.findByRequestId(onInitModel.getResp().getRequestId());
		abdmTransaction.setResponseBody(json.toJson(onInitModel));
		abdmTransactionRepo.save(abdmTransaction);
	}

	public void onConfirm(OnConfirmModel onConfirmModel) {
		ABDMTransaction abdmTransaction = abdmTransactionRepo.findByRequestId(onConfirmModel.getResp().getRequestId());
		abdmTransaction.setResponseBody(json.toJson(onConfirmModel));
		abdmTransactionRepo.save(abdmTransaction);
	}

	public void careContext(CareContextModel careContextModel) {
		ABDMTransaction abdmTransaction = abdmTransactionRepo
				.findByRequestId(careContextModel.getResp().getRequestId());
		abdmTransaction.setResponseBody(json.toJson(careContextModel));
		abdmTransactionRepo.save(abdmTransaction);
	}

	public void notify(NotifyRequestModel notifyRequestModel) {
		ABDMTransaction abdmTransaction = new ABDMTransaction();
		abdmTransaction.setRequestId(notifyRequestModel.getRequestId());
		abdmTransaction.setRequestBody(json.toJson(notifyRequestModel));
		abdmTransaction.setTimeStamp(notifyRequestModel.getTimestamp());
		abdmTransaction.setStatus(2);
		abdmTransactionRepo.save(abdmTransaction);

	}
}
