package app.controller;

import java.util.Locale;
import java.util.ResourceBundle;


public class TranslationService {
    /**
     * The name of the file that contains the translations.
     */
    private static String baseName = "consoleOutput";
    /**
     * Enum with two values, EN and PT, and each value has a Locale associated with it.
     */
    public enum Language {
        EN(new Locale("en","US")),
        PT(new Locale("pt","PT"));

        /**
         * A constructor that takes a Locale as a parameter and assigns it to the locale variable.
         */
        Language(Locale locale) {
            this.locale = locale;
        }

        final Locale locale;

        /**
         * Returns the locale of the language
         *
         * @return The locale of the language
         */
        Locale getLocale() {
            return locale;
        }
    }

    /**
     * Language of the TranslationService.
     *
     * The default language is English.
     */
    private static Language language = Language.EN;

    /**
     * This function returns the language of the TranslationService.
     *
     * @return The language variable is being returned.
     */
    public static Language getLanguage() {
        return language;
    }

    /**
     * It sets the language to the language passed in as a parameter
     *
     * @param lang The language to set the translation service to.
     */
    public static void setLanguage(Language lang) {
        TranslationService.language = lang;
        setResourceBundle(lang);
    }
    
    /**
     * Creating a resource bundle with the base name and the locale of the language.
     */
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, language.getLocale());

    /**
     * It sets the resource bundle to the one that corresponds to the language that was passed in
     *
     * @param lang The language you want to set the resource bundle to.
     */
    private static void setResourceBundle(Language lang) {
        TranslationService.resourceBundle = ResourceBundle.getBundle(baseName, language.getLocale());
    }

    /**
     * Get the text for the given key from the resource bundle.
     *
     * @param key The key to the string resource
     * @return The value of the key.
     */
    public String getText(String key) {
        return resourceBundle.getString(key);
    }
}
