package com.baldev.putify.views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageButton;

import com.baldev.putify.mvps.MessagesMVP;
import com.baldev.putify.R;
import com.baldev.putify.mvps.MessagesMVP.Presenter;
import com.baldev.putify.presenters.MessagesPresenter;
import com.baldev.putify.adapters.MessagesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MessagesActivity extends AppCompatActivity implements MessagesMVP.View {

	@BindView(R.id.edit_text_message) EditText messageEditText;
	@BindView(R.id.recycler_view_messages) RecyclerView messagesRecyclerView;
	@BindView(R.id.button_send_message) ImageButton sendButton;

	private MessagesAdapter adapter;
	private Presenter presenter = new MessagesPresenter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_send_message);
		ButterKnife.bind(this);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setReverseLayout(true);
		this.messagesRecyclerView.setLayoutManager(layoutManager);
		this.adapter = new MessagesAdapter();
		this.messagesRecyclerView.setAdapter(this.adapter);

		this.presenter.setAdapter(this.adapter);

		this.clearMessageField();
	}

	@Override
	@OnTextChanged(value = R.id.edit_text_message)
	public void onTextChanged(CharSequence text) {
		this.setSendButtonEnabled(!text.toString().equals(""));
	}

	@Override
	public void setSendButtonEnabled(boolean enabled) {
		this.sendButton.setEnabled(enabled);
		this.sendButton.setAlpha(enabled ? 1f : 0.4f);
	}

	@Override
	@OnClick(R.id.button_send_message)
	public void onSendPressed() {
		this.presenter.sendMessage(this, this.messageEditText.getText());
		this.clearMessageField();
	}

	private void clearMessageField() {
		this.messageEditText.setText("");
	}
}
