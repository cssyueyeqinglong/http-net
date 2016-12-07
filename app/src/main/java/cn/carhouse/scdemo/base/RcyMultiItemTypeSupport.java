package cn.carhouse.scdemo.base;

public interface RcyMultiItemTypeSupport<T>
{
	int getLayoutId(int type);
	int getItemViewType(int postion, T t);
}