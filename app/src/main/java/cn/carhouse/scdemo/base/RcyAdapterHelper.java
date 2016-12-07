package cn.carhouse.scdemo.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/11.
 */
public abstract class RcyAdapterHelper<T> extends
		RecyclerView.Adapter<RcyBaseHolder> {
	private int layoutId;
	private List<T> mDatas;



	public RcyAdapterHelper(int layoutId, List<T> data) {
		this.layoutId = layoutId;
		this.mDatas = data == null ? new ArrayList<T>()
				: new ArrayList<T>(data);
	}

	@Override
	public RcyBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View mView = LayoutInflater.from(parent.getContext()).inflate(layoutId,
				parent, false);
		RcyBaseHolder mAdapter = new RcyBaseHolder(mView);
		return mAdapter;
	}

	@Override
	public void onBindViewHolder(RcyBaseHolder holder, int position) {
		convert(holder, mDatas.get(position), position);
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	public abstract void convert(RcyBaseHolder holder, T item, int position);

	public T getItem(int position) {
		if (position >= mDatas.size())
			return null;
		return mDatas.get(position);
	}

	public void add(T elem) {
		mDatas.add(elem);
		notifyDataSetChanged();
	}

	public void addAll(List<T> elem) {
		mDatas.addAll(elem);
		notifyDataSetChanged();
	}

	public void set(T oldElem, T newElem) {
		set(mDatas.indexOf(oldElem), newElem);
	}

	public void set(int index, T elem) {
		mDatas.set(index, elem);
		notifyDataSetChanged();
	}

	public void remove(T elem) {
		mDatas.remove(elem);
		notifyDataSetChanged();
	}

	public void remove(int index) {
		mDatas.remove(index);
		notifyDataSetChanged();
	}

	public void replaceAll(List<T> elem) {
		mDatas.clear();
		mDatas.addAll(elem);
		notifyDataSetChanged();
	}

	public boolean contains(T elem) {
		return mDatas.contains(elem);
	}

	/**
	 * Clear data list
	 */
	public void clear() {
		mDatas.clear();
		notifyDataSetChanged();
	}

	public List<T> getDatas(){
		return mDatas;
	}
}
