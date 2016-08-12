package com.baldev.putify.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.baldev.putify.mvps.MessagesMVP;
import com.baldev.putify.R;
import com.baldev.putify.mvps.MessagesMVP.Presenter;
import com.baldev.putify.presenters.MessagesPresenter;
import com.baldev.putify.adapters.MessagesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessagesActivity extends AppCompatActivity implements MessagesMVP.View {

	@BindView(R.id.edit_text_message) EditText messageEditText;
	@BindView(R.id.recycler_view_messages) RecyclerView messagesRecyclerView;
	private Presenter presenter = new MessagesPresenter(this);
	protected MessagesAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		ButterKnife.bind(this);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setReverseLayout(true);
		messagesRecyclerView.setLayoutManager(layoutManager);
		adapter = new MessagesAdapter();
		messagesRecyclerView.setAdapter(adapter);

		presenter.setAdapter(adapter);

	}


	@Override
	@OnClick(R.id.button_send_message)
	public void onSendPressed() {
		presenter.sendMessage(this, messageEditText.getText());
		messageEditText.setText("");
	}
}
