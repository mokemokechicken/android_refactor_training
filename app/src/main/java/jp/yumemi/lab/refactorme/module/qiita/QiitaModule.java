package jp.yumemi.lab.refactorme.module.qiita;

import jp.yumemi.lab.refactorme.base.FragmentRouter;
import jp.yumemi.lab.refactorme.base.ModelContainer;
import jp.yumemi.lab.refactorme.base.Module;
import jp.yumemi.lab.refactorme.module.qiita.model.ModelTag;
import jp.yumemi.lab.refactorme.module.qiita.model.QiitaItemModel;
import jp.yumemi.lab.refactorme.module.qiita.presentation.DetailFragment;
import jp.yumemi.lab.refactorme.module.qiita.presentation.FragmentTag;
import jp.yumemi.lab.refactorme.module.qiita.presentation.LatestItemFragment;

/**
 * Created by k_morishita on 15/08/13.
 */
public class QiitaModule implements Module {

    @Override
    public void onLoad() {
        // ModelManager に各モデルの参照を登録
        ModelContainer.register(ModelTag.QIITA_ITEM, new QiitaItemModel());

        // FragmentManager に各Fragmentのクラス名を登録(ここではインスタンス化しない)
        FragmentRouter.instance.register(FragmentTag.LIST, LatestItemFragment.class);
        FragmentRouter.instance.register(FragmentTag.DETAIL, DetailFragment.class);
    }
}
