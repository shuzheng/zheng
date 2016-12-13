/**
 * SyntaxHighlighter
 * http://alexgorbatchev.com/
 *
 * SyntaxHighlighter is donationware. If you are using it, please donate.
 * http://alexgorbatchev.com/wiki/SyntaxHighlighter:Donate
 *
 * @version
 * 2.1.382 (June 24 2010)
 * 
 * @copyright
 * Copyright (C) 2004-2009 Alex Gorbatchev.
 *
 * @license
 * This file is part of SyntaxHighlighter.
 * 
 * SyntaxHighlighter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SyntaxHighlighter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with SyntaxHighlighter.  If not, see <http://www.gnu.org/copyleft/lesser.html>.
 */
SyntaxHighlighter.brushes.Erlang = function()
{
	// Contributed by Jean-Lou Dupont
	// http://jldupont.blogspot.com/2009/06/erlang-syntax-highlighter.html  

	// According to: http://erlang.org/doc/reference_manual/introduction.html#1.5
	var keywords = 'after and andalso band begin bnot bor bsl bsr bxor '+
		'case catch cond div end fun if let not of or orelse '+
		'query receive rem try when xor'+
		// additional
		' module export import define';

	this.regexList = [
		{ regex: new RegExp("[A-Z][A-Za-z0-9_]+", 'g'), 			css: 'constants' },
		{ regex: new RegExp("\\%.+", 'gm'), 						css: 'comments' },
		{ regex: new RegExp("\\?[A-Za-z0-9_]+", 'g'), 				css: 'preprocessor' },
		{ regex: new RegExp("[a-z0-9_]+:[a-z0-9_]+", 'g'), 			css: 'functions' },
		{ regex: SyntaxHighlighter.regexLib.doubleQuotedString,		css: 'string' },
		{ regex: SyntaxHighlighter.regexLib.singleQuotedString,		css: 'string' },
		{ regex: new RegExp(this.getKeywords(keywords),	'gm'),		css: 'keyword' }
		];
};

SyntaxHighlighter.brushes.Erlang.prototype	= new SyntaxHighlighter.Highlighter();
SyntaxHighlighter.brushes.Erlang.aliases	= ['erl', 'erlang'];
