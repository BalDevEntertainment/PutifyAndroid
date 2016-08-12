package com.baldev.putify.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baldev.putify.R;
import com.baldev.putify.model.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesAdapter extends RecyclerView.Adapter {

	List<Message> messages = new ArrayList<>();

	@Override
	public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
		MessageHolder messageHolder = new MessageHolder(view);
		return messageHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		Message message = messages.get(position);

		MessageHolder messageHolder = (MessageHolder) holder;
		messageHolder.body.setText(message.getText());
	}

	@Override
	public int getItemCount() {
		return messages.size();
	}

	public void addMessage(Message newMessage) {
		this.messages.add(0, newMessage);
		this.notifyDataSetChanged();
	}

	public static class MessageHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		@BindView(R.id.text_view_message_body) public TextView body;

		public MessageHolder(View view) {
			super(view);
			ButterKnife.bind(this, view);
		}
	}

}
