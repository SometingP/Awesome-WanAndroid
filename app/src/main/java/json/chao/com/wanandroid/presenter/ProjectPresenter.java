package json.chao.com.wanandroid.presenter;

import javax.inject.Inject;

import json.chao.com.wanandroid.core.DataManager;
import json.chao.com.wanandroid.base.RxPresenter;
import json.chao.com.wanandroid.contract.ProjectContract;
import json.chao.com.wanandroid.core.bean.BaseResponse;
import json.chao.com.wanandroid.core.bean.ProjectClassifyResponse;
import json.chao.com.wanandroid.utils.RxUtils;
import json.chao.com.wanandroid.widget.BaseObserver;

/**
 * @author quchao
 * @date 2018/2/11
 */

public class ProjectPresenter extends RxPresenter<ProjectContract.View> implements ProjectContract.Presenter {

    private DataManager mDataManager;

    @Inject
    ProjectPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(ProjectContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getProjectClassifyData() {
        addSubscribe(mDataManager.getProjectClassifyData()
                        .compose(RxUtils.rxSchedulerHelper())
                        .subscribeWith(new BaseObserver<ProjectClassifyResponse>(mView) {
                            @Override
                            public void onNext(ProjectClassifyResponse projectClassifyResponse) {
                                if (projectClassifyResponse.getErrorCode() == BaseResponse.SUCCESS) {
                                    mView.showProjectClassifyData(projectClassifyResponse);
                                } else {
                                    mView.showProjectClassifyDataFail();
                                }
                            }
                        }));
    }
}
