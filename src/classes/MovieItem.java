package classes;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

@Retention(RetentionPolicy.RUNTIME)
@interface WhatMovieItem {
  
}
@WhatMovieItem
public class MovieItem extends Item implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@WhatMovieItem
	public String formats;
	
	@WhatMovieItem
	public String actors;
	
	@WhatMovieItem
	public String lang;
	
	@WhatMovieItem
	public String subtitles;
	
	@WhatMovieItem
	public String region;
	
	@WhatMovieItem
	public String aspect;
	
	@WhatMovieItem
	public int discs;
	
	@WhatMovieItem
	public Date release;
	
	@WhatMovieItem
	public int runtime;
	
	@WhatMovieItem
	public String ASIN;

	public MovieItem(int id, int sales, String name, int categ, int scateg,
			double price, String imgUrl) {
		super(id, name, sales, scateg, categ, imgUrl, price);
	}

	public void addInfo(String actors, String formats, String language,
			String subtitles, String region, String aspect, int numberofdiscs,
			Date release, int runtime, String asin) {
		this.discs = numberofdiscs;
		this.release = release;
		this.runtime = runtime;
		this.ASIN = asin;
		this.actors = actors;
		this.formats = formats;
		this.lang = language;
		this.subtitles = subtitles;
		this.region = region;
		this.aspect = aspect;

	}


	public String getFormats() {
		return formats;
	}

	public String getActors() {
		return actors;
	}

	public String getLang() {
		return lang;
	}

	public String getSubtitles() {
		return subtitles;
	}

	public String getRegion() {
		return region;
	}

	public String getAspect() {
		return aspect;
	}

	public int getDiscN() {
		return discs;
	}

	public Date getRelease() {
		return release;
	}

	public int getRuntime() {
		return runtime;
	}

	public String getASIN() {
		return ASIN;
	}
	public String getInfo() {
		return "Actoress: " + actors + "Formato: " + formats + "Language: "
				+ lang;
	}
}
