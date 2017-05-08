package com.zhf.service;

import com.zhf.entity.Result;

public interface ShareService {
	public Result addShare(String noteId,String shareTitle,String shareBody);
	public Result searchNote(String content);
	public Result seeShare(String noteId);
}
