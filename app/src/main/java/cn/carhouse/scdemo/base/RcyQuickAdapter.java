package cn.carhouse.scdemo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/11.
 */
public abstract class RcyQuickAdapter<T> extends
		RecyclerView.Adapter<RcyBaseHolder> {
	private int layoutId;
	private int lastAnimatedPosition = -1;
	private List<T> mDatas;
	RcyMultiItemTypeSupport<T> mMultiItemSupport;
	private int mPosition;
	private RcyBaseHolder mAdapter;
	protected Context mTContext;

	public RcyQuickAdapter(List<T> data,
						   RcyMultiItemTypeSupport<T> multiItemSupport) {
		this.mDatas = data == null ? new ArrayList<T>()
				: new ArrayList<T>(data);
		this.mMultiItemSupport = multiItemSupport;
	}

	public RcyQuickAdapter(List<T> data,
						   RcyMultiItemTypeSupport<T> multiItemSupport, Context context) {
		this.mTContext = context;
		this.mDatas = data == null ? new ArrayList<T>()
				: new ArrayList<T>(data);
		this.mMultiItemSupport = multiItemSupport;
	}

	public RcyQuickAdapter(List<T> data, int layoutId) {
		this.mDatas = data == null ? new ArrayList<T>()
				: new ArrayList<T>(data);
		this.layoutId = layoutId;
	}

	public RcyQuickAdapter(List<T> data, int layoutId, Context context) {
		this.mTContext = context;
		this.mDatas = data == null ? new ArrayList<T>()
				: new ArrayList<T>(data);
		this.layoutId = layoutId;
	}

	@Override
	public RcyBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (mMultiItemSupport != null) {
			layoutId = mMultiItemSupport.getLayoutId(viewType);
		}
		View mView = LayoutInflater.from(parent.getContext()).inflate(layoutId,
				parent, false);
		mAdapter = new RcyBaseHolder(mView);
		return mAdapter;
	}

	@Override
	public void onBindViewHolder(RcyBaseHolder holder, int position) {
//        runEnterAnimation(holder.itemView, position);
		convert(holder, mDatas.get(position), position);
	}

	@Override
	public int getItemViewType(int position) {
		mPosition = position;
		if (mMultiItemSupport != null){
			try{
				return mMultiItemSupport.getItemViewType(position,mDatas.get(position));
			}catch (Exception e){

			}
		}
		return super.getItemViewType(position);
	}

	@Override
	public int getItemCount() {
		return mDatas.size();
	}

	public abstract void convert(RcyBaseHolder helper, T item, int position);

	public void add(T elem) {
		mDatas.add(elem);
		notifyDataSetChanged();
	}

	public void addFirst(T elem) {
		mDatas.add(0, elem);
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


	public List<T> getDatas() {
		return mDatas;
	}
}
